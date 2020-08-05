package cc.tong.system.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 16:37
 * @Description:
 */
public interface UserDataPermission extends IService<UserDataPermission> {


    /**
     * 通过部门ID删除关联关系
     *
     * @param deptIds 部门id
     */
    void deleteByDeptIds(List<String> deptIds);

    /**
     * 通过用户ID删除关联关系
     *
     * @param userIds 用户id
     */
    void deleteByUserIds(String[] userIds);

    /**
     * 通过用户ID查找关联关系
     *
     * @param userId 用户id
     * @return 关联关系
     */
    String findByUserId(String userId);




}
