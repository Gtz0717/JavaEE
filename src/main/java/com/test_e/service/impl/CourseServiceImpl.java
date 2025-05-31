package com.test_e.service.impl;

import com.test_e.entity.Course;
import com.test_e.dao.CourseDao;
import com.test_e.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseMapper;

    @Override
    @Transactional
    public void addCourse(Course course) {
        courseMapper.insertCourse(course);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        courseMapper.updateCourse(course);
    }

    @Override
    @Transactional
    public void deleteCourse(Integer courseId) {
        courseMapper.deleteCourse(courseId);
    }

    @Override
    public Course getCourseById(Integer courseId) {
        return courseMapper.selectCourseById(courseId);
    }

    @Override
    public List<Course> getCourseList(String semester, String keyword) {
        return courseMapper.selectCourseList(semester, keyword);
    }

    @Override
    public List<Course> getCoursesByTeacher(Integer teacherId) {
        return courseMapper.selectCoursesByTeacher(teacherId);
    }

    public List<Course> getCoursesByStudent(Integer studentId) {
        return  courseMapper.selectCoursesByStudent(studentId);
    }
}