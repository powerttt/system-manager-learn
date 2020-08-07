package cc.tong.system.service.impl;

import cc.tong.system.entity.User;
import cc.tong.system.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 16:09
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {


    @Override
    public User findByName(String username) {
        User user = new User();

        user.setUsername("管理员");
        user.setUserId(1L);
        user.setPassword("123456");
        return user;
    }

}
