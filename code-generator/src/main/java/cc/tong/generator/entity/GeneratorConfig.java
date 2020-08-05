package cc.tong.generator.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: tn
 * @Date: 2020/7/31 0031 13:36
 * @Description:
 */
@Data
public class GeneratorConfig {

    private String author;
    private String tableName;
    /**
     * 基础路径
     */
    private String basePackage;
    /**
     * 实体类 路径
     */
    private String entityPackage;

    private String tableComment;
    private String className;

    private transient boolean hasDate;
    private transient boolean hasBigDecimal;
    /**
     * 字段
     */
    private transient List<GeneratorColumn> columns;

    /**
     * java文件路径，固定值
     */
    private transient String javaPath = "/src/main/java/";
    /**
     * 配置文件存放路径，固定值
     */
    private transient String resourcesPath = "src/main/resources";

    private transient String date = "2020-07-31";

}
