package com.mgnote.mgnote.service;

import com.mgnote.mgnote.model.BriefUser;
import com.mgnote.mgnote.model.User;

public interface UserService {
    /**
     * 用户登录
     * @param userName 用户名
     * @param password 密码
     * @return 返回用户信息
     */
    User login(String userName, String password);

    /**
     * 用户注册，添加新的用户信息
     * @param user 用户信息
     * @return 生成的唯一用户id
     */
    String addUser(User user);

    /**
     * 根据用户id获取用户信息
     * @param userId 用户id
     * @return  用户信息
     */
    User getUserById(String userId);

    /**
     * 关注另一个用户
     * @param userId 用户id
     * @param otherId 被关注的用户id
     */
    void addFocus(String userId, String otherId);

    /**
     * 更改用户信息
     * @param userId 用户id
     * @param user 更新后的用户信息
     */
    void updateUser(String userId, User user);
}
