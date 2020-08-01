package com.test.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * TEST Entity
 *
 * @author test
 * @date 2020-07-31
 */
@Data
@TableName("t_job_log")
public class TJobLog {

    /**
     * 任务日志id
     */
    @TableId(value = "LOG_ID", type = IdType.AUTO)
    private Long logId;

    /**
     * 任务id
     */
    @TableField("JOB_ID")
    private Long jobId;

    /**
     * spring bean名称
     */
    @TableField("BEAN_NAME")
    private String beanName;

    /**
     * 方法名
     */
    @TableField("METHOD_NAME")
    private String methodName;

    /**
     * 参数
     */
    @TableField("PARAMS")
    private String params;

    /**
     * 任务状态    0：成功    1：失败
     */
    @TableField("STATUS")
    private String status;

    /**
     * 失败信息
     */
    @TableField("ERROR")
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    @TableField("TIMES")
    private BigDecimal times;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

}
