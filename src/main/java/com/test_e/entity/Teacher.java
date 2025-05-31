package com.test_e.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Teacher {
    private  Integer teacher_id;
    private String teacher_no;
    private String title;
    private String department;
    private String college;
    private String gender;
    private String birth_date;


    private User user;
}
