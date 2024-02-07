package com.home.homectl.mapper;

import com.home.homectl.dto.MachineDTO;
import com.home.homectl.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MachineMapper {
    public List<MachineDTO> get_all_machine(UserDTO user);

    public void delete_machine_by_uuid(MachineDTO machineDTO);

    public void machine_state_update(MachineDTO machineDTO);
}
