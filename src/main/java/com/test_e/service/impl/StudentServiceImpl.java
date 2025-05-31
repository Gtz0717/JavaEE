package com.test_e.service.impl;

import com.test_e.dao.StudentDao;
import com.test_e.dao.UserDao;
import com.test_e.entity.Student;
import com.test_e.entity.User;
import com.test_e.dao.StudentDao;
import com.test_e.dao.UserDao;
import com.test_e.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentMapper;

    @Autowired
    private UserDao userMapper;

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Integer studentId) {
        return studentMapper.selectStudentById(studentId);
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        // 更新学生详细信息
        studentMapper.updateStudent(student);
//
//        // 如果需要同时更新用户基本信息
//        User user = new User();
//        user.setUserId(student.getStudent_Id());
//        user.setRealName(student.getReal_Name()); // 假设Student中有realName字段
//        userMapper.updateUserBasicInfo(user);
    }
}