package com.home.homectl.mapper;

import com.home.homectl.dto.MachineDTO;
import com.home.homectl.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public UserDTO find_user(UserDTO user);

    void user_register(UserDTO userDTO);
}
