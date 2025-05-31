package com.test_e.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data                   // 自动生成 getter/setter/toString
@NoArgsConstructor      // 无参构造器
@AllArgsConstructor     // 全参构造器
public class TeacherCourse {
    private String teacherNo;
    private String userName;
    private String courseName;
}





