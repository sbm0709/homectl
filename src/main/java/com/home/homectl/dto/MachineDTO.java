package com.home.homectl.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineDTO {
    private String uuid;
    private String name;
    private int state;
    private String type;
    private String user_id;
}
