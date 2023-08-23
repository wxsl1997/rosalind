package com.wxsl.rosalind;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 快速生成
 * </p>
 *
 * @author lanjerry
 * @since 2021-09-16
 */
@Disabled
public class AutoGeneratorTest {
    private static final String MAPPER_LOCATION = "com/wxsl/rosalind/mybatis/mapper/";
    private static final String PROJECT = "com.wxsl.rosalind";
    private static final String MODULE = "mybatis";
    private static final String AUTHOR = "wxsl";
    private static final String TABLE_PREFIX = "";
    private static final boolean OVERRIDE = false;

    @CsvSource({"user", "trade_info", "order_info", "product"})
    @ParameterizedTest
    public void execute(String tables) {

        String projectPath = System.getProperty("user.dir");

        AutoGenerator generator = new AutoGenerator();
        //全局配置
        GlobalConfig globalConfig = globalConfig(projectPath);
        generator.setGlobalConfig(globalConfig);

        //数据源配置
        DataSourceConfig dataSourceConfig = dataSourceConfig();
        generator.setDataSource(dataSourceConfig);

        //策略配置
        StrategyConfig strategyConfig = strategyConfig(tables);
        generator.setStrategy(strategyConfig);

        //包名策略配置
        PackageConfig packageConfig = packageConfig();
        generator.setPackageInfo(packageConfig);

        // 注入自定义配置
        InjectionConfig injectionConfig = getInjectionConfig(projectPath);
        generator.setCfg(injectionConfig);

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("");
        templateConfig.setServiceImpl("");
        templateConfig.setService("");
        templateConfig.setXml("");
        generator.setTemplate(templateConfig);

        // 执行生成
        generator.execute();

    }

    private static InjectionConfig getInjectionConfig(String projectPath) {
        List<FileOutConfig> fileOutConfigs = new ArrayList<>();

        fileOutConfigs.add(new FileOutConfig("/templates/mapper.xml.vm") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/" + MAPPER_LOCATION + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        return new InjectionConfig() {
            @Override
            public void initMap() {
                this.setFileOutConfigList(fileOutConfigs);
            }
        };
    }

    private static DataSourceConfig dataSourceConfig() {
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://wxsl5.com:3306/rosalind_mybatis")
                .setUsername("root")
                .setPassword("1250");
    }

    private static StrategyConfig strategyConfig(String tables) {
        List<TableFill> tableFills = ImmutableList.<TableFill>builder()
                .add(new TableFill("created", FieldFill.INSERT))
                .add(new TableFill("modified", FieldFill.INSERT_UPDATE))
                .build();
        return new StrategyConfig()
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setVersionFieldName("version")
                .setTableFillList(tableFills)
                .setEntityTableFieldAnnotationEnable(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setSkipView(true)
                .setInclude(tables)
                .setTablePrefix(TABLE_PREFIX);
    }

    private static PackageConfig packageConfig() {
        return new PackageConfig()
                .setParent(PROJECT)
                .setModuleName(MODULE);
    }

    private static GlobalConfig globalConfig(String projectPath) {
        return new GlobalConfig()
                .setOpen(false)
                .setAuthor(AUTHOR)
                .setOutputDir(projectPath + "/src/main/java")//代码生成路径
                .setFileOverride(OVERRIDE)
                .setIdType(IdType.INPUT)
                .setDateType(DateType.TIME_PACK)
                .setServiceName("%sService");
    }
}