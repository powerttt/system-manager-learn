package cc.tong.security.config.shiro.jwt;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 16:58
 * @Description:
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {


    /**
     * 是否是登录接口，检测header里面是否包含Authorization字段即可
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        return req.getHeader(JWTUtil.AUTHORIZATION) != null;
    }

    /**
     * 登入
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(JWTUtil.AUTHORIZATION);
        JWTToken token = new JWTToken(authorization);
        // 提交给shiroRealm进入登入，如果错误会抛出错误
        getSubject(request, response).login(token);
        return true;
    }

    /**
     * 是否允许通过，为true
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                unauthorized(response);
            }

        }
        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpServletRequest servletRequest = (HttpServletRequest) request;

        servletResponse.setHeader("Access-control-Allow-Origin", servletRequest.getHeader("Origin"));
        servletResponse.setHeader("Access-control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        servletResponse.setHeader("Access-control-Allow-Headers", servletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域是发送 OPTIONS 请求，直接返回正常状态
        if (servletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            servletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 没权限
     */
    public void unauthorized(ServletResponse response) {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

}
