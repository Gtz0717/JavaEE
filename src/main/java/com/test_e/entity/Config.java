package com.test_e.entity;
import lombok.*;


@Data
@Builder
public class Config {
    private String config_id;
    private String config_key;
    private String config_value;
    private String description;
}

