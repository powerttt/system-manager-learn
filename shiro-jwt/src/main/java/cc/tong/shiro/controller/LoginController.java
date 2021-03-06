package cc.tong.shiro.controller;

import cc.tong.common.ResponseBean;
import cc.tong.shiro.config.shiro.jwt.JWTUtil;
import cc.tong.system.entity.User;
import cc.tong.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 * @author: tn
 * @Date: 2020/8/3 0003 08:46
 * @Description:
 */
@Validated
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final IUserService userService;

    @PostMapping("login")
    public Object login(@NotBlank(message = "{required}") String username,
                      @NotBlank(message = "{required}") String password,
                      boolean rememberMe, HttpServletResponse response) {
        User byName = userService.findByName(username);
        return JWTUtil.sign(username,password);
    }

    @PostMapping
    public String test() {
        return "hello";
    }


    @GetMapping("/article")
    public ResponseBean article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {

            return new ResponseBean().success();
        } else {
            return new ResponseBean().success().message("not authed");
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean().success().message("You are authenticated");
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseBean requireRole() {
        return new ResponseBean().success().message("有");
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseBean requirePermission() {
        return new ResponseBean().success().message("您正在访问权限，需要编辑，查看");
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean().code(HttpStatus.UNAUTHORIZED).message(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
}
