package cc.tong.security.handler;

import cc.tong.security.utils.ServletUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: tn
 * @Date: 2020/8/6 0006 15:28
 * @Description:
 */
@Component
public class AccessDeniedServletImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        String msg = String.format("请求 %s 失败，无法访问系统资源1", request.getRequestURI());
        ServletUtils.renderString(response, msg);
    }
}
