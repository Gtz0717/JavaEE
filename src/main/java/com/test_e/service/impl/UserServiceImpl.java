package com.test_e.service.impl;

import com.test_e.entity.User;
import com.test_e.exception.BusinessException;
import com.test_e.dao.UserDao;
import com.test_e.service.UserService;
import com.test_e.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userMapper;

    @Override
    public User login(String username, String password) {
        // 根据用户名查询用户
        System.out.println("尝试登录的用户名: " + username);
        User user = userMapper.selectByUsername(username);
        System.out.println(user);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

//        // 验证密码
//        if (!PasswordUtil.matches(password, user.getPassword())) {
//            throw new BusinessException("用户名或密码错误");
//        }


        // 验证密码
        if (!password.equals(user.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }



        // 返回用户信息(不包含密码)
        user.setPassword(null);
        return user;
    }

    @Override
    @Transactional
    public void changePassword(Integer userId, String oldPassword, String newPassword) {
        // 获取用户当前信息
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 验证旧密码
        if (!PasswordUtil.matches(oldPassword, user.getPassword())) {
            throw new BusinessException("旧密码不正确");
        }

        // 更新密码
        String encryptedPassword = PasswordUtil.encode(newPassword);
        userMapper.updatePassword(userId, encryptedPassword);
    }

    @Override
    public User getUserById(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            user.setPassword(null); // 不返回密码信息
        }
        return user;
    }

    @Override
    public User validateUser(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}