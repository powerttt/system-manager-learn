package cc.tong.security.utils.ip;

import cc.tong.security.utils.Constants;
import cc.tong.security.utils.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: tn
 * @Date: 2020/8/5 0005 16:41
 * @Description:
 */
@Slf4j
public class AddressUtils {

    /**
     * IP 查询
     * if(window.IPCallBack) {IPCallBack({"ip":"183.158.79.11","pro":"浙江省","proCode":"330000","city":"杭州市","cityCode":"330100","region":"","regionCode":"0","addr":"浙江省杭州市 电信","regionNames":"","err":""});}
     */
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";
    // 是否开启网址查询
    public static boolean ADDRESS_ENABLED = true;

    public static String getRealAddressByIP(String ip) {

        String address = UNKNOWN;
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
        if (StringUtils.isBlank(rspStr)) {
            log.error("获取地理位置异常 {}", ip);
            return UNKNOWN;
        }
        JSONObject object = JSON.parseObject(rspStr);
//        String region = object.getString("pro");
//        String city = object.getString("city");
        String addr = object.getString("addr");
        return addr;
    }
}
