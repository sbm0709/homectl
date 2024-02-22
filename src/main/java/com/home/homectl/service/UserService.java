package com.home.homectl.service;

import com.home.homectl.dto.UserDTO;
import com.home.homectl.mapper.UserMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@org.springframework.stereotype.Service
@Log4j2
public class UserService {

    final private int TOKEN_DEADLINE_MINUTE = 10;
    final private String SEND_MAIL_FROM = "sbm0709@naver.com";
    private final String RESET_PASSWORD_URL = "http://localhost:8080/user/reset/pw?token=";


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void user_register(UserDTO userDTO){
        userDTO.setPw(passwordEncoder.encode(userDTO.getPassword()));
        userMapper.user_register(userDTO);
    }

    public boolean duplicateCheck(UserDTO userDTO){
        UserDTO user = userMapper.find_user(userDTO);
        return user == null;
    }

    public String find_user_id(String tel){
       UserDTO findUser = userMapper.find_user_by_tel(tel);
       if(Objects.isNull(findUser)) return "해당 전화번호로 등록된 아이디가 존재하지않습니다.";
       else {
            return "찾은 아이디 : "+findUser.getId();
       }
    }

    public String find_user_pw(UserDTO userDTO){
        UserDTO findUser = userMapper.find_user(userDTO);
        if(Objects.isNull(findUser)) return "해당 아이디가 존재하지않습니다.";
        else {
            try {
                findUser.setPasswordToken(UUID.randomUUID().toString());
                findUser.setTokenDeadline(LocalDateTime.now().plusMinutes(TOKEN_DEADLINE_MINUTE));
                findUser.setEmail(userDTO.getEmail());
                log.error(findUser);
                userMapper.update_user_token(findUser);
                send_email_for_password(findUser);
                return "메시지가 성공적으로 발송되었습니다.";
            }
            catch (Exception e){
                return "메세지 발송 중 오류가 발생하였습니다.";
            }
        }
    }

    private void send_email_for_password(UserDTO userDTO) throws MessagingException {
        Context context = new Context();
        String token = userDTO.getPasswordToken();
        context.setVariable("link",RESET_PASSWORD_URL+token);
        String htmlContent = templateEngine.process("/mail/reset_templates.html",context); //메일에 보이는 창

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true, "UTF-8");
        mimeMessageHelper.setFrom(SEND_MAIL_FROM);
        mimeMessageHelper.setTo(userDTO.getEmail());
        mimeMessageHelper.setSubject("[HOME CARE] 비밀번호 재설정");
        mimeMessageHelper.setText(htmlContent,true);

        javaMailSender.send(mimeMessage);
    }

    public boolean find_user_by_token(String token){
        UserDTO findUser = userMapper.find_user_by_token(token);
        return Objects.nonNull(findUser) && !findUser.getTokenDeadline().isBefore(LocalDateTime.now());
    }
    public void resetting_password(String token, String password){
        String pw = passwordEncoder.encode(password);
        userMapper.resetting_password(token,pw);
    }
}
