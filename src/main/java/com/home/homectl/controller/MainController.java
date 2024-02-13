package com.home.homectl.controller;


import com.home.homectl.dto.MachineDTO;
import com.home.homectl.dto.UserDTO;
import com.home.homectl.mapper.MachineMapper;
import com.home.homectl.service.MachineService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Log4j2
@Controller
public class MainController {

    @Autowired
    private MachineService machineService;

    @GetMapping("/")
    public String get_main(
            @AuthenticationPrincipal UserDTO user,
            Model model

    ){
        if(!Objects.isNull(user)){
            List<MachineDTO> machineList = machineService.get_all_machine(user);
            log.error(machineList);
            model.addAttribute("machines",machineList);
            return "main/main";
        }
        else{
            return "user/login";
        }
    }


}
