package com.home.homectl.mapper;

import com.home.homectl.dto.MachineDTO;
import com.home.homectl.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    public UserDTO find_user(UserDTO user);

    void user_register(UserDTO userDTO);

    @Select("SELECT * FROM `user` WHERE `tel` = ${tel}")
    UserDTO find_user_by_tel(String tel);

    void update_user_token(UserDTO userDTO);

    UserDTO find_user_by_token(String token);

    void resetting_password(String token, String pw);
}
