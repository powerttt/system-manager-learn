package cc.tong.security.handler;

import cc.tong.security.utils.ServletUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登入处理类
 *
 * @author: tn
 * @Date: 2020/8/5 0005 14:37
 * @Description:
 */
@Component
public class LogoutHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {


        // 去除request中的用户信息，拿到用户名，对登入缓存进行处理
        // 删除用户缓存记录
        // 记录用户退出日志

        ServletUtils.renderString(httpServletResponse, "退出成功");

    }
}
