package com.home.homectl.controller;


import com.home.homectl.dto.UserDTO;
import com.home.homectl.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String get_login(){
        return "user/login";
    }

    @GetMapping("/register")
    public String get_register(){return "user/register";}

    @PostMapping("/register")
    public String user_register(
            @Validated UserDTO userDTO,
            BindingResult result
    ){
        if(result.hasErrors()){
            log.error("User register-error");
            return "redirect:/user/register";
        }
        else {
            userService.user_register(userDTO);
            return "redirect:/user/login";
        }
    }

    @GetMapping("/check/{user}")
    @ResponseBody
    public boolean duplicateCheck(
            @PathVariable("user") String id){
        UserDTO user = UserDTO.builder().id(id).build();
        return userService.duplicateCheck(user);
    }
}
