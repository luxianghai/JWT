package cn.sea.service;

import cn.sea.entity.User;

public interface UserService {

    // 根据用户名和密码登录
    User login(User user);
}
