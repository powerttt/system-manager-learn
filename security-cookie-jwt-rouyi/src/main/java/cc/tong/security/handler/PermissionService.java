package cc.tong.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: tn
 * @Date: 2020/8/6 0006 15:36
 * @Description:
 */
@Service("ss")
public class PermissionService {

    @Autowired
    private ITokenService tokenService;


    public final boolean hasAuthority(String authority) {
        return true;
    }


}
