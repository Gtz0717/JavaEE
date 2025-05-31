package com.test_e.service.impl;

import com.test_e.entity.Course;
import com.test_e.entity.Teaching_class;
import com.test_e.exception.BusinessException;
import com.test_e.dao.CourseDao;
import com.test_e.dao.TeachingClassMapper;
import com.test_e.service.TeachingClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TeachingClassServiceImpl implements TeachingClassService {

    @Autowired
    private TeachingClassMapper teachingClassMapper;

    @Autowired
    private CourseDao courseMapper;

    @Override
    @Transactional
    public void createTeachingClass(Teaching_class teachingClass) {
        // 验证课程是否存在且属于该教师
        Course course = courseMapper.selectCourseById(teachingClass.getCourse_id());
        if (course == null || !course.getTeacherId().equals(teachingClass.getTeacher_id())) {
            throw new BusinessException("无权为该课程创建教学班");
        }

        // 检查教学班名称是否重复
        Teaching_class existing = teachingClassMapper.selectByCourseAndName(
                teachingClass.getCourse_id(), teachingClass.getClass_name());
        if (existing != null) {
            throw new BusinessException("该课程下已存在同名教学班");
        }

        teachingClassMapper.insertTeachingClass(teachingClass);
    }

    @Override
    @Transactional
    public void updateTeachingClass(Teaching_class teachingClass) {
        // 获取原有教学班信息
        Teaching_class oldClass = teachingClassMapper.selectTeachingClassById(teachingClass.getClass_id());
        if (oldClass == null) {
            throw new BusinessException("教学班不存在");
        }

        // 更新教学班信息
        teachingClassMapper.updateTeachingClass(teachingClass);
    }

    @Override
    public Teaching_class getTeachingClassById(Integer classId) {
        return teachingClassMapper.selectTeachingClassById(classId);
    }

    @Override
    public List<Teaching_class> getTeachingClassesByCourse(Integer courseId) {
        return teachingClassMapper.selectTeachingClassesByCourse(courseId);
    }

    @Override
    public List<Teaching_class> getTeachingClassesByTeacher(Integer teacherId) {
        return teachingClassMapper.selectTeachingClassesByTeacher(teacherId);
    }
}