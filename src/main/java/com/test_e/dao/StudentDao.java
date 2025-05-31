package com.test_e.dao;

import com.test_e.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentDao {
    /**
     * 根据ID查询学生信息
     */
    Student selectStudentById(@Param("studentId") Integer studentId);

    /**
     * 更新学生信息
     */
    int updateStudent(Student student);


}