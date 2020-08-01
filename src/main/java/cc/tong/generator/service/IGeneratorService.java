package cc.tong.generator.service;

import cc.tong.generator.entity.GeneratorColumn;
import cc.tong.generator.entity.GeneratorTable;

import java.util.List;

/**
 * @author: tn
 * @Date: 2020/7/31 0031 10:16
 * @Description:
 */
public interface IGeneratorService {
    /**
     * 查询数据库下面的表
     *
     * @param schemaName
     * @return 表
     */
    List<GeneratorTable> getTables(String schemaName, String tableName);

    /**
     * 查询数据库表下面的列属性
     *
     * @param schemaName
     * @param tableName
     * @return
     */
    List<GeneratorColumn> getColumns(String schemaName, String tableName);

    /**
     * 查询可用数据库
     *
     * @return
     */
    List<String> getDatabases();
}
