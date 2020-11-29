package cn.sea.dao;

import cn.sea.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper // 加了这个注解就不用在入口类上配置扫描包了
@Repository
public interface UserDAO {

    // 根据用户名和密码登录
    User login(User user);
}
