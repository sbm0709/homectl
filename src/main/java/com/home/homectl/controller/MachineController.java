package com.home.homectl.controller;


import com.home.homectl.dto.MachineDTO;
import com.home.homectl.dto.UserDTO;
import com.home.homectl.service.MachineService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Log4j2
@Controller
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;


    @ResponseBody
    @DeleteMapping("/delete")
    public void machine_delete(
            @AuthenticationPrincipal UserDTO userDTO,
            @RequestBody MachineDTO machineDTO
    ){
        log.error(machineDTO);
//        machineService.delete_machine_by_uuid(machineDTO);

    }

    @ResponseBody
    @PatchMapping("/state")
    public void machine_state_update(
            @AuthenticationPrincipal UserDTO userDTO,
            @RequestBody MachineDTO machineDTO
    ){
        log.error(machineDTO);
        machineService.machine_state_update(machineDTO);
    }
    @ResponseBody
    @PatchMapping("/name")
    public void machine_name_update(
            @AuthenticationPrincipal UserDTO userDTO,
            @RequestBody MachineDTO machineDTO
    ){
        log.error(machineDTO);
        machineService.machine_state_update(machineDTO);
    }
}
