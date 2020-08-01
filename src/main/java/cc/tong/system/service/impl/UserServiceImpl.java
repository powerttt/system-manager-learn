package cc.tong.system.service.impl;

import cc.tong.system.entity.User;
import cc.tong.system.mapper.UserMapper;
import cc.tong.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 16:09
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public User findByName(String username) {
        User user = new User();

        user.setUsername("管理员");
        user.setUserId(1L);
        return user;
    }

    @Override
    public User findUserDetailList(String username) {
        return null;
    }

    @Override
    public void deleteUsers(String[] userIds) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void resetPassword(String[] usernames) {

    }

    @Override
    public void regist(String username, String password) {

    }

    @Override
    public void updatePassword(String username, String password) {

    }
}
