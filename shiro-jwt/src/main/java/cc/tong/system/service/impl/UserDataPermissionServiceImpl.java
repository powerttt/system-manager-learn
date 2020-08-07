package cc.tong.system.service.impl;

import cc.tong.system.service.IUserDataPermissionService;
import org.springframework.stereotype.Service;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 16:38
 * @Description:
 */
@Service
public class UserDataPermissionServiceImpl implements IUserDataPermissionService {


    @Override
    public String findByUserId(String userId) {
        return "1,2,34";
    }
}
