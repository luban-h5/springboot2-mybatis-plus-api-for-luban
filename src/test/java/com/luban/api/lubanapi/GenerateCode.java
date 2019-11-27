package com.luban.api.lubanapi;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

public class GenerateCode {

    private final String url = "jdbc:mysql://dburl:13306/h5?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private final String username = "root";
    private final String password = "root";
    private final String driverName = "com.mysql.cj.jdbc.Driver";
    private final String author = "WeiHongBin";
    private final String outputDir = "C:\\Users\\admin\\Desktop\\luban-api\\src\\main\\java";
    private final String packageName = "com.luban.api.lubanapi";

    @Test
    public void getCode() {
        generateCode(
                "work", "work_forms"
        );
    }

    private void generateCode(String... table) {

        // 数据库配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig() {{
            setDbType(DbType.MYSQL);
            setUrl(url);
            setUsername(username);
            setPassword(password);
            setDriverName(driverName);
        }};
        // 策略配置项
        StrategyConfig strategyConfig = new StrategyConfig() {{
            setRestControllerStyle(true);
            setCapitalMode(true);
            setEntityLombokModel(true);
            setControllerMappingHyphenStyle(true);
            setNaming(NamingStrategy.underline_to_camel);
            setInclude(table);

        }};
        // 全局配置
        GlobalConfig config = new GlobalConfig() {{
            // setFileOverride(true);
            setActiveRecord(false);
            setSwagger2(true);
            setAuthor(author);
            setIdType(IdType.AUTO);
            setEnableCache(false);
            setBaseResultMap(true);
            setXmlName("%sDao");
            setMapperName("%sDao");
            setOutputDir(outputDir);
            setServiceName("%sService");
        }};
        //包信息
        PackageConfig packageInfo = new PackageConfig() {{
            setParent(packageName);
            setEntity("entity");
            setMapper("dao");
            setXml("dao");
            setService("service");
            setServiceImpl("service.impl");
            setController("controller");
        }};
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageInfo)
                .execute();
    }

}
