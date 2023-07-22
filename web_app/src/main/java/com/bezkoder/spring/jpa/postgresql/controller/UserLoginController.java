package com.bezkoder.spring.jpa.postgresql.controller;

import com.bezkoder.spring.jpa.postgresql.model.UserLogin;
import com.bezkoder.spring.jpa.postgresql.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/loginUser")
public class UserLoginController {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @GetMapping(value = "/createLogin")
    public String createLogin(){
        UserLogin userLogin = new UserLogin();
        userLogin.setName("poovarasan");
        userLogin.setPassword("0909");
//        userLoginRepository.save(userLogin);
        return "login user created";
    }
    @GetMapping(value = "/getUser")
    public List<UserLogin> getUser() {
        return userLoginRepository.findAll();
    }

}
