package com.test_e.service.impl;

import com.test_e.dao.UserDao;
import com.test_e.entity.Teacher;
import com.test_e.entity.User;
import com.test_e.exception.BusinessException;
import com.test_e.dao.TeacherDao;
import com.test_e.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherMapper;

    @Autowired
    private UserDao userMapper;

    @Override
    public Teacher getTeacherById(Integer teacherId) {
        Teacher teacher = teacherMapper.selectTeacherById(teacherId);
        if (teacher != null) {
            // 关联用户基本信息
            User user = userMapper.selectById(teacherId);
            teacher.setUser(user);
        }
        return teacher;
    }

    @Override
    @Transactional
    public void updateTeacher(Teacher teacher) {
        // 验证教师是否存在
        Teacher existing = teacherMapper.selectTeacherById(teacher.getTeacher_id());
        if (existing == null) {
            throw new BusinessException("教师不存在");
        }

        // 更新教师信息
        teacherMapper.updateTeacher(teacher);

        // 如果需要同时更新用户基本信息
        if (teacher.getUser() != null) {
            User user = teacher.getUser();
            user.setUserId(teacher.getTeacher_id());
            userMapper.updateUserBasicInfo(user);
        }
    }
}