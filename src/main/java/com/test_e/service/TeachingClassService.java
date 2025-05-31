package com.test_e.service;

import com.test_e.entity.Teaching_class;
import java.util.List;

public interface TeachingClassService {
    /**
     * 创建教学班
     * @param teachingClass 教学班信息
     */
    void createTeachingClass(Teaching_class teachingClass);

    /**
     * 更新教学班信息
     * @param teachingClass 教学班信息
     */
    void updateTeachingClass(Teaching_class teachingClass);

    /**
     * 根据ID获取教学班
     * @param classId 教学班ID
     * @return 教学班信息
     */
    Teaching_class getTeachingClassById(Integer classId);

    /**
     * 获取课程的教学班列表
     * @param courseId 课程ID
     * @return 教学班列表
     */
    List<Teaching_class> getTeachingClassesByCourse(Integer courseId);

    /**
     * 获取教师的教学班列表
     * @param teacherId 教师ID
     * @return 教学班列表
     */
    List<Teaching_class> getTeachingClassesByTeacher(Integer teacherId);
}