package com.test_e.entity;

import lombok.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Course {
    private Integer courseId;
    private String courseCode;
    private String courseName;
    private Integer teacherId;
    private Double credit;
    private String classTime;
    private String classroom;
    private Integer capacity;
    private Integer selected;
    private String status;
    private String semester;
    private String courseType;
    private String description;
    private Date createTime;
    private Date updateTime;

    // Getter/Setter省略
}