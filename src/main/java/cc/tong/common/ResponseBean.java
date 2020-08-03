package cc.tong.common;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author: tn
 * @Date: 2020/8/3 0003 15:37
 * @Description:
 */
public class ResponseBean extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public ResponseBean code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public ResponseBean message(String message) {
        this.put("message", message);
        return this;
    }

    public ResponseBean data(Object data) {
        this.put("data", data);
        return this;
    }

    public ResponseBean success() {
        this.code(HttpStatus.OK);
        return this;
    }

    public ResponseBean fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR);
        return this;
    }

    @Override
    public ResponseBean put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
