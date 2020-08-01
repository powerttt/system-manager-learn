package cc.tong.generator.entity;

import com.google.common.io.Files;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author: tn
 * @Date: 2020/7/31 0031 13:45
 * @Description:
 */
@Slf4j
@Component
public class GeneratorHelper {

    public void entityHelper(GeneratorConfig config, List<GeneratorColumn> columnList) throws Exception {

        String suffix = GenerotorConstents.SUFFIX_ENTITY;
        String path = getFilePath(config, config.getEntityPackage(), suffix);
        String entityTemplate = GenerotorConstents.TEMPLATE_NAME_ENTITY;
        File file = new File(path);
        columnList.forEach(column -> {
            column.setField(underScoreToCamel(column.getName().toLowerCase()));
            config.setHasDate(StringUtils.containsAny(column.getType(), FieldType.DATE, FieldType.TIMESTAMP, FieldType.DATETIME));
            config.setHasBigDecimal(StringUtils.containsAny(column.getType(), FieldType.DECIMAL, FieldType.NUMERIC));
        });
        config.setColumns(columnList);
        generatorFileByTemplate(entityTemplate, file, config);
    }

    private void generatorFileByTemplate(String templateName, File file, GeneratorConfig config) throws Exception {
        Template template = getTemplate(templateName);

        Files.createParentDirs(file);

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8), 10240)) {

            template.process(config, writer);

        } catch (Exception e) {

            String message = "代码生成异常";
            log.error(message, e);
            throw new Exception(message);
        }


    }

    /**
     * 文件路径
     *
     * @param config
     * @param packagePath
     * @param suffix
     * @return
     */
    private String getFilePath(GeneratorConfig config, String packagePath, String suffix) {
        String outFilePath = "temp/" + config.getJavaPath() + packageConvertPath(config.getBasePackage() + "." + packagePath);
        outFilePath += config.getClassName() + suffix;
        return outFilePath;
    }

    private Template getTemplate(String templateName) throws IOException {
        final String templatePathPrefix = File.separator + "generator" + File.separator;
        Configuration configuration = new freemarker.template.Configuration(Configuration.VERSION_2_3_23);
        ClassPathResource classPathResource = new ClassPathResource("generator");
        configuration.setDirectoryForTemplateLoading(classPathResource.getFile());
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return configuration.getTemplate(templateName);
    }

    private String packageConvertPath(String packageName) {
        return String.format("%s%s%s"
                , File.separator
                , packageName.contains(".") ? packageName.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) : packageName
                , File.separator);
    }

    /**
     * 下划线转驼峰命名
     *
     * @param name
     * @return
     */
    public static String underScoreToCamel(String name) {
        StringBuilder sb = new StringBuilder(name.length());
        String[] arr = name.split("_");
        for (String s : arr) {
            sb.append(String.valueOf(s.charAt(0)).toUpperCase()).append(s.substring(1));
        }
        return sb.toString();
    }

}
