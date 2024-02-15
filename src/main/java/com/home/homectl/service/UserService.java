package com.home.homectl.service;

import com.home.homectl.dto.UserDTO;
import com.home.homectl.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
@Log4j2
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void user_register(UserDTO userDTO){
        userMapper.user_register(userDTO);
    }

    public boolean duplicateCheck(UserDTO userDTO){
        UserDTO user = userMapper.find_user(userDTO);
        return user == null;
    }
}
