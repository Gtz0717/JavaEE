package com.test_e.entity;
import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private int student_Id;
    private String student_no;
    private String class_name;
    private Date birth_date;
    private String major;
    private String grade;
    private String college;
    private String  gender;
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}