package cc.tong.system.service;


/**
 * @author MrBird
 */
public interface IUserDataPermissionService {


    /**
     * 通过用户ID查找关联关系
     *
     * @param userId 用户id
     * @return 关联关系
     */
    String findByUserId(String userId);

}
