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


}
