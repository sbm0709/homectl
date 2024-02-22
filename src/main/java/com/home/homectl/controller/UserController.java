package com.home.homectl.controller;


import com.home.homectl.dto.UserDTO;
import com.home.homectl.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


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

    @GetMapping("/find")
    public String get_find_page(){
        return "user/findaccount";
    }

    @PostMapping("/find/id")
    @ResponseBody
    public String user_find_id(
            @RequestBody String tel
    ){
        return userService.find_user_id(tel);
    }

    @PostMapping("/find/pw")
    @ResponseBody
    public String user_find_pw(
            @RequestBody UserDTO userDTO
    ){
        return userService.find_user_pw(userDTO);
    }

    @GetMapping("/reset/pw")
    public String get_password_reset_page(
            @RequestParam("token") String token,
            Model model
    ){
        if(userService.find_user_by_token(token)){
            model.addAttribute("token",token);
            return "mail/password_reset";
        }
        return "error/password_token_missing";
    }

    @PostMapping("/reset/pw")
    public String reset_password(
            @RequestParam("token")String token,
            @RequestParam("pw") String pw
    ){
        userService.resetting_password(token,pw);
        return "/user/login";
    }
}
