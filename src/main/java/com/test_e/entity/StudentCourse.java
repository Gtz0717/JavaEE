package com.test_e.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {
    private int studentNo;
    private String userName;
    private String courseName;
    private String courseNO;

}
