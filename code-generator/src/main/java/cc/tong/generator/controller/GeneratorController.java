package cc.tong.generator.controller;

import cc.tong.generator.entity.GeneratorColumn;
import cc.tong.generator.entity.GeneratorConfig;
import cc.tong.generator.entity.GeneratorHelper;
import cc.tong.generator.entity.GeneratorTable;
import cc.tong.generator.service.IGeneratorService;
import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: tn
 * @Date: 2020/7/31 0031 10:44
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("generator")
@RequiredArgsConstructor
public class GeneratorController {

    private final IGeneratorService iGeneratorService;
    private final DynamicDataSourceProperties properties;
    private final GeneratorHelper generatorHelper;

    @GetMapping("datasource")
    public List<String> datasource() {
        Map<String, DataSourceProperty> datasource = properties.getDatasource();
        log.info(JSON.toJSONString(datasource));
        return datasource.values().stream().map(v -> StringUtils.substringBefore(StringUtils.substringAfterLast(v.getUrl(), "/"), "?")).collect(Collectors.toList());
    }

    @GetMapping("tables/info")
    public Object getTableInfo(String schemaName, String dataSource) {
        List<GeneratorTable> tables = iGeneratorService.getTables(schemaName, dataSource);
        return tables;
    }

    @GetMapping("tables/column")
    public Object getColumn(String schemaName, String tableName) {
        List<GeneratorColumn> columns = iGeneratorService.getColumns(schemaName, tableName);
        return columns;
    }

    @GetMapping("entity")
    public Object generatorEntity(String schemaName, String tableName) throws Exception {
        List<GeneratorColumn> columns = iGeneratorService.getColumns(schemaName, tableName);
        GeneratorConfig generatorConfig = new GeneratorConfig();
        generatorConfig.setAuthor("test");
        generatorConfig.setTableName(tableName);
        generatorConfig.setBasePackage("com.test");
        generatorConfig.setEntityPackage("entity");
        generatorConfig.setTableComment("TEST");
        generatorConfig.setClassName(GeneratorHelper.underScoreToCamel(tableName));
//        Config
        generatorHelper.entityHelper(generatorConfig, columns);
        return null;
    }


}
