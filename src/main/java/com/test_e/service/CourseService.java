package com.test_e.service;

import com.test_e.entity.Course;
import java.util.List;

public interface CourseService {
    /**
     * 添加课程
     * @param course 课程信息
     */
    void addCourse(Course course);

    /**
     * 更新课程信息
     * @param course 课程信息
     */
    void updateCourse(Course course);

    /**
     * 删除课程
     * @param courseId 课程ID
     */
    void deleteCourse(Integer courseId);

    /**
     * 根据ID获取课程详情
     * @param courseId 课程ID
     * @return 课程信息
     */
    Course getCourseById(Integer courseId);

    /**
     * 获取课程列表
     * @param semester 学期
     * @param keyword 搜索关键字
     * @return 课程列表
     */
    List<Course> getCourseList(String semester, String keyword);

    /**
     * 获取教师教授的课程
     * @param teacherId 教师ID
     * @return 课程列表
     */
    List<Course> getCoursesByTeacher(Integer teacherId);

    /**
     * 获取学生教授的课程
     * @param studentId 教师ID
     * @return 课程列表
     */
    List<Course> getCoursesByStudent(Integer studentId);
}