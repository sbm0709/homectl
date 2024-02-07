package com.home.homectl.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping("/login")
    public String get_login(){
        return "user/login";
    }
}
