package com.jamersc.springboot.todoexpense.controller;

import com.jamersc.springboot.todoexpense.dao.UserDao;
import com.jamersc.springboot.todoexpense.entity.User;
import com.jamersc.springboot.todoexpense.service.UserService;
import com.jamersc.springboot.todoexpense.validation.ManageUser;
import com.jamersc.springboot.todoexpense.validation.LoginUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {


    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    // init binder & resolve issues for validation
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    @GetMapping("/login")
    public String showLogin(Model model) {

        model.addAttribute("loginUser", new LoginUser());

        return "todo-expense/login";
    }

    @PostMapping("/login")
    public String processLoginForm(@Valid @ModelAttribute("loginUser") LoginUser loginUser,
                                   BindingResult result, Model model){


        if (result.hasErrors()) {

            return "todo-expense/login";

        }
        else {

            User user = new User();

            user.setUsername(loginUser.getLoginUsername());
            user.setPassword(loginUser.getLoginPassword());

            model.addAttribute("user", user);

            System.out.println("Login Username: " + user.getUsername());

            return "todo-expense/dashboard";
        }

    }
    // redirect to create account page
    @GetMapping("/createAccount")
    public  String createAccount(Model model) {

        model.addAttribute("user", new ManageUser());
        return "./forms/create-account-form";

    }

    @PostMapping("/createAccount")
    public String processCreateAccount(@Valid @ModelAttribute("user") ManageUser createAccount,
                                       BindingResult result, Model model) {

            System.out.println("New User Details: " + createAccount);

            if (result.hasErrors()) {

                return "./forms/create-account-form";

            }
            else {

                User user = new User();

                user.setFirstName(createAccount.getFirstName());
                user.setLastName(createAccount.getLastName());
                user.setGender(createAccount.getGender());
                user.setEmail(createAccount.getEmail());
                user.setUsername(createAccount.getUsername());
                user.setPassword(createAccount.getPassword());

                userDao.save(user);

                model.addAttribute("user", user);

                System.out.println("Created Account: " + user);

                return "todo-expense/new-user-page";
            }

    }

    /* Index/Dashboard Page */
    @GetMapping("/dashboard")
    public String showIndex() {
        return "todo-expense/dashboard";
    }

    @GetMapping("/users-management")
    public String showUsersManagement(Model model) {

        List<User> users = userDao.findAll();

        model.addAttribute("users", users);

        // logs
        for (User tempUsers : users) {
            System.out.println(tempUsers);
        }

        return "todo-expense/users-management";
    }

    @GetMapping("/createUser")
    public String createUser(Model model) {

        model.addAttribute("user", new ManageUser());
        return "./forms/user-management-form";
    }

    @PostMapping("/createUser")
    public String processCreateUser(@Valid @ModelAttribute("user") ManageUser createUser,
                                    BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "./forms/user-management-form";
        }
        else {

            User user = new User();

            user.setFirstName(createUser.getFirstName());
            user.setLastName(createUser.getLastName());
            user.setEmail(createUser.getEmail());
            user.setGender(createUser.getGender());
            user.setUsername(createUser.getUsername());
            user.setPassword(createUser.getPassword());

            userDao.save(user);

            model.addAttribute("user", user);

            return "redirect:/users/users-management";
        }
    }

    @GetMapping("/updateUser")
    public String updateUser(@RequestParam("userId") Integer userId, Model model) {

        User id = userDao.findById(userId);

        model.addAttribute("user", id);
        return "./forms/user-management-update-form";
    }

    @PostMapping("/updateUser")
    public String processUpdateUser(@Valid @ModelAttribute("user") ManageUser updateUser,
                                    BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "./forms/user-management-update-form";
        }
        else {

            User user = userDao.findById(updateUser.getId());

            if (user != null) {

                user.setFirstName(updateUser.getFirstName());
                user.setLastName(updateUser.getLastName());
                user.setEmail(updateUser.getEmail());
                user.setGender(updateUser.getGender());
                user.setUsername(updateUser.getUsername());
                user.setPassword(updateUser.getPassword());

                userDao.update(user);

                model.addAttribute("user", user);

                return "redirect:/users/users-management";
            }

            return "redirect:/users/users-management";
        }
    }



    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Integer id) {

        userDao.deleteById(id);

        return "redirect:/users/users-management";
    }

}
