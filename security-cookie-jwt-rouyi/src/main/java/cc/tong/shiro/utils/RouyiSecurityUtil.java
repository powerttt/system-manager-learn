package cc.tong.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author: tn
 * @Date: 2020/8/5 0005 17:34
 * @Description:
 */
public class RouyiSecurityUtil {


    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
