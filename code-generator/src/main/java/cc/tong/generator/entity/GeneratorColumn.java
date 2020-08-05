package cc.tong.generator.entity;

import lombok.Data;

/**
 * @author: tn
 * @Date: 2020/7/31 0031 10:20
 * @Description:
 */
@Data
public class GeneratorColumn {

    /**
     * 名称
     */
    private String name;
    /**
     * 是否为主键
     */
    private Boolean isKey;
    /**
     * 类型
     */
    private String type;
    /**
     * 注释
     */
    private String remark;
    /**
     * 属性名称
     */
    private String field;


}
