package com.test_e.dao;

import com.test_e.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
        /**
         * 根据用户名查询用户
         */
        User selectByUsername(String username);

        /**
         * 根据ID查询用户
         */
        User selectById(Integer userId);

        /**
         * 更新用户密码
         */
        int updatePassword(@Param("userId") Integer userId,
                           @Param("password") String password);


        int updateUserBasicInfo(User user);

        //查询学生和教师的详细信息
        User selectStudentWithDetails(@Param("userId") Integer userId);
        User selectTeacherWithDetails(@Param("userId") Integer userId);
}