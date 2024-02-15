package com.home.homectl.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PowerLogDTO {
    private int power;
    private LocalDateTime time;
    private String uuid;
}
