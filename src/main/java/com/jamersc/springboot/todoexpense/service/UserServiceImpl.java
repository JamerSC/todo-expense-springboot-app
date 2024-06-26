package com.jamersc.springboot.todoexpense.service;

import com.jamersc.springboot.todoexpense.dto.LoginUser;
import com.jamersc.springboot.todoexpense.dto.ManageUser;
import com.jamersc.springboot.todoexpense.model.User;
import com.jamersc.springboot.todoexpense.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository){
        this.userRepository = theUserRepository;
    }

    @Override
    public User loginUser(LoginUser loginUser) {
        // implement login logic
        return userRepository.findByUsernameAndPassword(
                loginUser.getLoginUsername(), loginUser.getLoginPassword());
    }

    @Override
    public User manageUser(ManageUser manageUser) {
        return userRepository.findByUsername(manageUser.getUsername());
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public ManageUser findUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            ManageUser manageUser = new ManageUser();
            BeanUtils.copyProperties(user, manageUser);
            return manageUser;
        }
        return null;
    }

    @Override
    public void saveUser(ManageUser manageUser) {
        User user;
        // check manageUser id if not equals to empty;
        if (manageUser.getId() != null) {
            // Existing user: preserve createDate
            user = userRepository.findById(manageUser.getId()).orElse(new User());
        } else {
            // New user: create a new user object
            user = new User();
        }
        // Copy properties from manageUser to user, excluding createDate
        BeanUtils.copyProperties(manageUser, user, "createdAt");
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer theId) {
        userRepository.deleteById(theId);
    }
}
