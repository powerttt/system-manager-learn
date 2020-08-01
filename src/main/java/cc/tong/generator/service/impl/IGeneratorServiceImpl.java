package cc.tong.generator.service.impl;

import cc.tong.generator.entity.GeneratorColumn;
import cc.tong.generator.entity.GeneratorTable;
import cc.tong.generator.mapper.GeneratorMapper;
import cc.tong.generator.service.IGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: tn
 * @Date: 2020/7/31 0031 10:17
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class IGeneratorServiceImpl implements IGeneratorService {
    @Autowired
    private GeneratorMapper generatorMapper;

    @Override
    public List<GeneratorTable> getTables(String schemaName, String tableName) {
        return generatorMapper.getTables(schemaName, tableName);
    }

    @Override
    public List<GeneratorColumn> getColumns(String schemaName, String tableName) {
        return generatorMapper.getColumns(schemaName, tableName);
    }

    @Override
    public List<String> getDatabases() {
        return generatorMapper.getDatabases();
    }
}
