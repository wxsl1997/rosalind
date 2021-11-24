package com.wxsl.rosalind.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 读取MBG配置生成代码, 子项目需配置 edit configurations -> working directory 路径
 */
public class Generator {

    public static void main(String[] args) throws Exception {

        List<String> warnings = new ArrayList<>();

        InputStream is = Generator.class.getClassLoader().getResourceAsStream("generatorConfig.xml");

        ConfigurationParser cp = new ConfigurationParser(warnings);

        Configuration configuration = cp.parseConfiguration(is);

        Objects.requireNonNull(is).close();

        DefaultShellCallback shellCallback = new DefaultShellCallback(true);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, shellCallback, warnings);

        myBatisGenerator.generate(new NullProgressCallback());

        warnings.forEach(System.out::println);
    }
}
