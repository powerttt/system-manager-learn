package cc.tong.security.controller;

import cc.tong.security.entity.LoginUser;
import cc.tong.security.handler.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tn
 * @Date: 2020/8/6 0006 08:38
 * @Description:
 */
@RestController
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ITokenService tokenService;


    @PostMapping("login")
    public Object login(String username, String password) {

        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return "账号密码错误";
        } catch (AuthenticationException e) {
            e.printStackTrace();

        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return tokenService.createToken(loginUser);

    }

    @PostMapping("login/test")
    @PreAuthorize("@ss.hasAuthority('ttt')")
    public Object loginTest() {
        return "HELLO";
    }

    @GetMapping("test")
    @PreAuthorize("@ss.hasAuthority('ttt1')")
    public Object test() {
        return "HELLO TEST";
    }


}
