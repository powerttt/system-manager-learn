package cc.tong.system.service;

import cc.tong.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 16:07
 * @Description:
 */
public interface IUserService extends IService<User> {

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findByName(String username);


    /**
     * 通过用户名查找用户详细信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findUserDetailList(String username);

    /**
     * 删除用户
     *
     * @param userIds 用户 id数组
     */
    void deleteUsers(String[] userIds);

    /**
     * 修改用户
     *
     * @param user user
     */
    void updateUser(User user);

    /**
     * 重置密码
     *
     * @param usernames 用户名数组
     */
    void resetPassword(String[] usernames);

    /**
     * 注册用户
     *
     * @param username 用户名
     * @param password 密码
     */
    void regist(String username, String password);

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param password 新密码
     */
    void updatePassword(String username, String password);
}
