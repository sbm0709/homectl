package com.home.homectl.service;

import com.home.homectl.dto.UserDTO;
import com.home.homectl.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Log4j2
@Service
public class CustomUserdetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDTO tempuserDTO = UserDTO.builder()
                .id(username)
                .build();
        UserDTO userDTO = userMapper.find_user(tempuserDTO);

        log.error(userDTO);
        if(Objects.isNull(userDTO)){
            throw new UsernameNotFoundException("해당 username이 존재하지 않음 " + "["+ username +"]");
        }

        return userDTO;
    }
}
