package cc.tong.config.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 16:08
 * @Description:
 */
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }


}
