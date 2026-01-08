package com.amadeus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateLog {
    private int id;
    private int operate_user_id;
    private LocalDateTime operate_time;
    private String class_name;
    private String method_name;
    private String method_params;
    private String return_value;
    private Long cost_time;
}
