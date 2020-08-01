package cc.tong.config.shiro;

import cc.tong.config.shiro.jwt.JWTToken;
import cc.tong.config.shiro.jwt.JWTUtil;
import cc.tong.system.entity.User;
import cc.tong.system.service.IUserDataPermissionService;
import cc.tong.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 16:05
 * @Description:
 */
@Slf4j
@Service
public class ShiroRealm extends AuthorizingRealm {

    private IUserService userService;
    private IUserDataPermissionService userDataPermissionService;


    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserDataPermissionService(IUserDataPermissionService userDataPermissionService) {
        this.userDataPermissionService = userDataPermissionService;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权模块，获取用户角色和权限
     *
     * @param principals principal
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String token = (String) principals.getPrimaryPrincipal();
        String username = JWTUtil.getUsername(token);
        log.info("username online: {}", username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        Set<String> roleSet = new HashSet<>(Arrays.asList("admin", "employee"));
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        Set<String> permissionSet = new HashSet<>(Arrays.asList("User::add", "User::delete"));
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param token AuthenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        // 查询用户信息
        Optional<User> userOptional = Optional.ofNullable(userService.findByName("admin"));

        if (!userOptional.isPresent() || !StringUtils.equals(password, userOptional.get().getPassword())) {
            throw new IncorrectCredentialsException("用户名密码错误");
        }
        User user = userOptional.get();
        if (User.STATUS_LOCK.equals(user.getStatus())) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        // 把用户信息塞shiro进去
        String deptIds = userDataPermissionService.findByUserId(String.valueOf(user.getUserId()));
        user.setDeptIds(deptIds);

        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 清除当前用户权限缓存
     * 使用方法：在需要清除用户权限的地方注入 ShiroRealm,
     * 然后调用其 clearCache方法。
     */
    public void clearCache() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principalCollection);
    }

}
