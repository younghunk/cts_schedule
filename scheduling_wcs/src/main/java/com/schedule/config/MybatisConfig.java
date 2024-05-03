package com.schedule.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MybatisConfig {

    @Autowired
    @Qualifier(value = "orgDataSource")
    private DataSource orgDataSource;
    
    
    @Bean
    @Primary
    public SqlSessionFactory orgSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(orgDataSource);
        /* 맵퍼 xml 파일 경로 설정 */
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/**/**.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        /* 실제DB컬럼명 스네이크 표기법 = 카멜케이스 표기법 맵핑 */
        configuration.setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory;
    }
    
    @Bean
    @Primary
    public SqlSession orgSqlSession() throws Exception {
        return new SqlSessionTemplate(orgSqlSessionFactoryBean());
    }
    
}