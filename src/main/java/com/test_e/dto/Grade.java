package com.test_e.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    private Long id;
    private String courseCode;
    private String courseName;
    private Integer credits;
    private Double score;
    private String semester;
    // 其他可能需要的字段...
}