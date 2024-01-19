package com.example.controllers;

import com.example.dto.StatesAndCapital;
import com.example.dto.User;
import com.example.service.StatesAndCapitalMappingService;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    UserService  userService;
    @Autowired
    StatesAndCapitalMappingService capitalMappingService;
    @RequestMapping("/test")
    public String test() {
        this.logger.warn("This is working message");
        return "Testing message";
    }

    @RequestMapping("users")
    public List<User> getUsers(){
        System.out.print("## Getting users .. ");
        return userService.getUserList();
    }

    @RequestMapping("getCapitalMapping")
    public List<StatesAndCapital> getStatesAndCapital(){
        return capitalMappingService.populateStatesandCapitalMapping();
    }
}