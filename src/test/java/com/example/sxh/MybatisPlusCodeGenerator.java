package com.example.sxh;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MybatisPlusCodeGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig(
                        (scanner, builder) -> builder.author(scanner.apply("请输入作者名称？"))
                                .outputDir(System.getProperty("user.dir") + "/src/main/java")
                                .fileOverride()

                )
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent("com.mfw.msp.xiaochetuan.dreamtrip.module.order")
                        .mapper("dao")
                        .xml("dao.xml")
                        .controller("controller")
                        .service("service")
                        .entity("entity")
                )
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(
                                getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .addTablePrefix("t_")
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .entityBuilder().disableSerialVersionUID().formatFileName("%sPO").enableLombok()
                        .mapperBuilder().formatMapperFileName("%sDao").formatXmlFileName("%sDao")
                        // .addTableFills(new Column("create_time", FieldFill.INSERT))
                        .build())
                .templateEngine(new FreemarkerTemplateEngine())
                .templateConfig(builder -> builder
                        .service("/templates/service.java")//"/templates/service.java"
                        .serviceImpl("/templates/serviceImpl.java")
                        .controller("/templates/controller.java"))
                .execute();

    }

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(
            "jdbc:mysql://182.92.95.61:3306/dream_trip?useSSL=false&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
            "root", "Aliyun@123456");

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
