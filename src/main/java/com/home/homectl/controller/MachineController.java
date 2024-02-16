package com.home.homectl.controller;


import com.home.homectl.dto.MachineDTO;
import com.home.homectl.dto.PowerLogDTO;
import com.home.homectl.dto.UserDTO;
import com.home.homectl.service.MachineService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        machineService.delete_machine_by_uuid(machineDTO);

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
        machineService.machine_name_update(machineDTO);
    }


    @ResponseBody
    @GetMapping("/log/{uuid}")
    public List<PowerLogDTO> get_machine_log(
            @PathVariable("uuid") String uuid
    ){
        return machineService.get_machine_log(uuid);
    }


    @ResponseBody
    @GetMapping("/{select}")
    public List<MachineDTO> get_machine_on_selct(
            @AuthenticationPrincipal UserDTO userDTO,
            @PathVariable("select") String select
    ){
        return machineService.get_all_machine(userDTO, select);
    }

    @PatchMapping("/get_img")
    @ResponseBody
    public String get_img(
            @AuthenticationPrincipal UserDTO userDTO,
            @RequestBody String uuid
    ){
        return machineService.get_img(userDTO.getId(),uuid);
    }
}
