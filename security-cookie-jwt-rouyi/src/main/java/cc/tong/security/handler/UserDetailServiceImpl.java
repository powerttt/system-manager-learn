package cc.tong.security.handler;

import cc.tong.security.entity.LoginUser;
import cc.tong.security.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author: tn
 * @Date: 2020/8/5 0005 14:17
 * @Description:
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
         SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }

         */


        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setPassword("$2a$10$uTTc3SYJPwK/4ug5Ai51zuC9OLbHVvTbjmJntW/bRbK34HJ1lxZYC");

        return new LoginUser(sysUser, new HashSet<>(Arrays.asList("*:*:*")));

    }
}
