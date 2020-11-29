package cn.sea.service.impl;

import cn.sea.dao.UserDAO;
import cn.sea.entity.User;
import cn.sea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {
        User login = userDAO.login(user);
        if ( login != null ) {
            return login;
        }
        throw new RuntimeException("认证失败 ~ ");
    }
}
