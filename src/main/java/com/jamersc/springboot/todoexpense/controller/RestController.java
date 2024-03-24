package com.jamersc.springboot.todoexpense.controller;


import com.jamersc.springboot.todoexpense.entity.User;
import com.jamersc.springboot.todoexpense.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
//@Controller
@RequestMapping("/api")
public class RestController {

    private UserService userService;

    public RestController(UserService theUserService){
        this.userService = theUserService;
    }

    // get all users
    @GetMapping("/users")
    public List<User> getAlUsers() {
        return userService.findAll();
    }

    // Search User by id
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {

        User theUser = userService.findById(userId);

        if(theUser == null) {
            throw new UserNotFoundException("The User id not found - " + userId);
        }
        return theUser;

    }

    @PostMapping("/users")
    public User createUser(@RequestBody User theUser) {

        // set new user id to zero, db will create its id automatic
        //theUser.setUserId(0);
        // save the user
        User dbUser = userService.save(theUser);

        // Set the createdBy and modifiedBy to the userId generated by the database
        dbUser.setCreatedBy(dbUser.getUserId());
        dbUser.setModifiedBy(dbUser.getUserId());

        dbUser = userService.save(dbUser);

        // return the query
        return dbUser;
    }

    // Show webpages
    @GetMapping("/showLoginSignup")
    public String showLoginSignup() {
        return "login-signup";
    }
    @GetMapping("/showIndex")
    public String showIndex() {
    // public String showIndex(Model theModel) {

        // theModel.addAttribute("theDate", new java.util.Date());

        return "index";
    }
    @GetMapping("/showTodo")
    public String showTodo() {
        return "todo";
    }

    @GetMapping("/showExpenses")
    public String showExpenses() {
        return "expenses";
    }
}
