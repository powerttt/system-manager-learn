package cc.tong.system.service.impl;

import cc.tong.system.entity.UserDataPermission;
import cc.tong.system.mapper.UserDataPermissionMapper;
import cc.tong.system.service.IUserDataPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 16:38
 * @Description:
 */
@Service
public class UserDataPermissionServiceImpl extends ServiceImpl<UserDataPermissionMapper, UserDataPermission> implements IUserDataPermissionService {
    @Override
    public void deleteByDeptIds(List<String> deptIds) {

    }

    @Override
    public void deleteByUserIds(String[] userIds) {

    }

    @Override
    public String findByUserId(String userId) {
        return "1,2,34";
    }
}
