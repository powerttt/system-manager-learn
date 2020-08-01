package cc.tong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: tn
 * @Date: 2020/7/31 0031 09:22
 * @Description:
 */
@MapperScan("cc.tong.generator.mapper")
@SpringBootApplication
public class FebsLearnApplication {
    public static void main(String[] args) {
        SpringApplication.run(FebsLearnApplication.class, args);
    }
}
