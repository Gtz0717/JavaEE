package com.test_e.service;

import com.test_e.entity.Student;

public interface StudentService {
    /**
     * 根据ID获取学生详细信息
     * @param studentId 学生ID
     * @return 学生信息
     */
    Student getStudentById(Integer studentId);

    /**
     * 更新学生信息
     * @param student 学生信息
     */
    void updateStudent(Student student);
}