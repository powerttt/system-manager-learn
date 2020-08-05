package cc.tong.generator.mapper;

import cc.tong.generator.entity.GeneratorColumn;
import cc.tong.generator.entity.GeneratorTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: tn
 * @Date: 2020/7/31 0031 10:18
 * @Description:
 */
public interface GeneratorMapper {

    /**
     * 查询数据库下面的表
     *
     * @param schemaName
     * @return
     */
    List<GeneratorTable> getTables(@Param("schemaName") String schemaName, @Param("tableName") String tableName);

    /**
     * 查询数据库表下面的列属性
     *
     * @param schemaName
     * @param tableName
     * @return
     */
    List<GeneratorColumn> getColumns(@Param("schemaName") String schemaName, @Param("tableName") String tableName);

    /**
     * 查询可用数据库
     *
     * @return
     */
    List<String> getDatabases();


}
