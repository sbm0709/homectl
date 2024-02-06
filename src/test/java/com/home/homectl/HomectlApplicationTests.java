package com.home.homectl;

import com.home.homectl.dto.UserDTO;
import com.home.homectl.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomectlApplicationTests {

	@Autowired
	private UserMapper userMapper;
	@Test
	void contextLoads() {
		UserDTO user = UserDTO.builder().id("test").build();
		userMapper.find_user(user);
	}

}
