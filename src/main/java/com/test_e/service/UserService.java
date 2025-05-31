package com.test_e.service;

import com.test_e.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    User login(String username, String password);

    /**
     * 修改密码
     * @param userId 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void changePassword(Integer userId, String oldPassword, String newPassword);

    /**
     * 根据ID获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    User getUserById(Integer userId);

    User validateUser(String username, String password);



}