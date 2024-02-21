package com.home.homectl.service;


import com.home.homectl.dto.MachineDTO;
import com.home.homectl.dto.PowerLogDTO;
import com.home.homectl.dto.UserDTO;
import com.home.homectl.mapper.MachineMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import java.net.http.*;
import java.util.List;
import java.util.Objects;

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

    public String get_img(String userId, String uuid){
        // 요청을 보낼 URL
        String url = "http://redcan.myds.me:8080/get_image?userId="+userId+"&uuid="+uuid;

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "*/*");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
        byte[] imageBytes = null;

        if(Objects.nonNull(responseEntity.getBody())){
         imageBytes = responseEntity.getBody();
        }

        return Base64.encodeBase64String(imageBytes);
    }

    public void insert_machine_log(PowerLogDTO powerLogDTO){machineMapper.insert_machine_log(powerLogDTO);}
}
