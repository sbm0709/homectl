package com.home.homectl.service;


import com.home.homectl.dto.MachineDTO;
import com.home.homectl.dto.UserDTO;
import com.home.homectl.mapper.MachineMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class MachineService {

    @Autowired
    private MachineMapper machineMapper;

    public List<MachineDTO> get_all_machine(UserDTO userDTO){
        return machineMapper.get_all_machine(userDTO);
    }
}
