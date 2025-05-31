package com.test_e.dao;

import com.test_e.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

@Mapper
public interface TeacherDao {
    /**
     * 根据ID查询教师信息
     */
    Teacher selectTeacherById(@Param("teacherId") Integer teacherId);

    /**
     * 更新教师信息
     */
    int updateTeacher(Teacher teacher);

}