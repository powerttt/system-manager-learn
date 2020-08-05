package cc.tong.system.service;


import cc.tong.system.entity.UserDataPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author MrBird
 */
public interface IUserDataPermissionService extends IService<UserDataPermission> {


    /**
     * 通过用户ID查找关联关系
     *
     * @param userId 用户id
     * @return 关联关系
     */
    String findByUserId(String userId);

}
