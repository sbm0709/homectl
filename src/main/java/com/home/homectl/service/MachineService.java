package com.home.homectl.service;


import com.home.homectl.dto.MachineDTO;
import com.home.homectl.dto.PowerLogDTO;
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

    public List<MachineDTO> get_all_machine(UserDTO userDTO, String select){
        return machineMapper.get_all_machine(userDTO, select);
    }

    public void delete_machine_by_uuid(MachineDTO machineDTO){
        machineMapper.delete_machine_by_uuid(machineDTO);
    }

    public void machine_state_update(MachineDTO machineDTO){
        machineMapper.machine_state_update(machineDTO);
    }

    public void machine_name_update(MachineDTO machineDTO){
        machineMapper.machine_name_update(machineDTO);
    }

    public List<PowerLogDTO> get_machine_log(String uuid){return machineMapper.get_machine_log(uuid);}
}
