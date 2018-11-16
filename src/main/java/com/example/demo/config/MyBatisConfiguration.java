package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * TODO 描述:
 *
 * @author xiaobin.wang@marketin.cn
 * @create 2018-11-15 22:28
 * @since 2.16.6
 */
@Configuration
public class MyBatisConfiguration {

    private static final Logger logger = LoggerFactory
            .getLogger(MyBatisConfiguration.class);

    @Bean(name = "sqlSessionFactory")
    @Autowired(required = true)
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
            throws Exception {
        logger.info("Create MyBatis SqlSessionFactory bean");
        SqlSessionFactoryBean ss = new SqlSessionFactoryBean();
        ss.setDataSource(dataSource);
        ss.setConfigLocation(
                new ClassPathResource("mybatis-config.xml"));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        ss.setMapperLocations(resolver.getResources(
                "classpath*:mapping/com/example/demo/mapper/*.xml"));
        return (SqlSessionFactory) ss.getObject();
    }


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.example.demo.mapper");
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return configurer;
    }

}