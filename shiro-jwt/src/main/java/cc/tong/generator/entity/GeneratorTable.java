package cc.tong.generator.entity;

import lombok.Data;

/**
 * @author: tn
 * @Date: 2020/7/31 0031 10:20
 * @Description:
 */
@Data
public class GeneratorTable {

    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据量（行）
     */
    private Long dataRows;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;


}
