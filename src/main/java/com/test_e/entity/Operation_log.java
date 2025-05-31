package com.test_e.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Operation_log {
    private int log_id;
    private int user_id;
    private String operation;
    private String method;
    private String params;
    private String ip;
    private String create_time;
}
