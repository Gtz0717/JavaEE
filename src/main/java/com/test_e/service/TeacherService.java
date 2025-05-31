package com.test_e.service;

import com.test_e.entity.Teacher;

public interface TeacherService {
    /**
     * 根据ID获取教师详细信息
     * @param teacherId 教师ID
     * @return 教师信息
     */
    Teacher getTeacherById(Integer teacherId);

    /**
     * 更新教师信息
     * @param teacher 教师信息
     */
    void updateTeacher(Teacher teacher);
}