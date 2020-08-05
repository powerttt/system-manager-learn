package cc.tong.handler;

import cc.tong.common.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Set;

/**
 * @author: tn
 * @Date: 2020/8/3 0003 09:51
 * @Description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 方法普通参数校验，实体对象校验使用 BandException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseBean handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            Path path = constraintViolation.getPropertyPath();
            String[] arr = StringUtils.splitPreserveAllTokens(path.toString(), ".");
            message.append(arr[1]).append(constraintViolation.getMessage()).append(",");
        }
        return new ResponseBean().code(HttpStatus.BAD_REQUEST).message(message.toString());
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseBean handleAuthorizationException(AuthorizationException e) {
        log.error("AuthorizationException, {}", e.getMessage());
        e.printStackTrace();
        return new ResponseBean().code(HttpStatus.UNAUTHORIZED).message(e.getMessage());
    }

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class, ShiroException.class})
    public ResponseBean handle401(ShiroException e) {
        e.printStackTrace();
        return new ResponseBean().code(HttpStatus.UNAUTHORIZED).message(e.getMessage());
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {UnauthorizedException.class, UnauthenticatedException.class})
    public ResponseBean handle401() {
        return new ResponseBean().code(HttpStatus.UNAUTHORIZED).message(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

}
