package com.fan.framework.config;

import com.fan.consts.InitConfig;
import com.fan.dao.interfaces.baseService.mapper.IUserMapper;
import com.fan.dao.model.basicService.User;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author:fanwenlong
 * @date:2017-12-28 10:16:09
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:数据库配置
 * @detail:用户数据库相关的操作
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataBaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataBaseConfig.class);

    /** 数据库的属性配置 */
    @Bean(name = "MysqlProperties")
    public Properties getMysqlProperties(){
        Properties properties = new Properties();
        properties.setProperty("driver", InitConfig.MYSQL_DRIVER);
        properties.setProperty("url",InitConfig.MYSQL_URL);
        properties.setProperty("username",InitConfig.MYSQL_USERNAME);
        properties.setProperty("password",InitConfig.MYSQL_PASSWORD);
        return properties;
    }

    /** 获取数据源 */
    @Bean
    public DataSource getBasicDataSource(@Qualifier("MysqlProperties") Properties properties){
        DataSource dataSource = null;
        try{
            dataSource = new PooledDataSource(properties.getProperty("driver"),
                                              properties.getProperty("url"),
                                              properties.getProperty("username"),
                                              properties.getProperty("password"));
        }catch (Exception e){
            logger.error("初始化数据源失败" + e.getMessage());
        }
        return dataSource;
    }

    /** 获取事务 */
    @Bean
    public TransactionFactory getTransactionFactory(){
        return new JdbcTransactionFactory();
    }


    /** 会话工厂的配置 */
    @Bean
    public org.apache.ibatis.session.Configuration getSqlConfiguration(TransactionFactory transactionFactory,DataSource dataSource){
        Environment environment = new Environment(InitConfig.MYSQL_ENVIRONMENT,transactionFactory,dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setEnvironment(environment);
        /** 扫描包中的所有映射文件 */
        configuration.addMappers("com.fan.dao.interfaces.baseService.mapper");
        return configuration;
    }

    /** 创建sql会话工厂 */
    @Bean(name = "MysqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(org.apache.ibatis.session.Configuration configuration){
        try {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
            return sqlSessionFactory;
        } catch (Exception e) {
            logger.error("初始化工厂失败" + e.getMessage());
            return null;
        }
    }

    /** 创建会话 */
    @Bean
    public SqlSession getMySqlSqlSession(@Qualifier("MysqlSessionFactory") SqlSessionFactory sf){
        SqlSession sqlSession = sf.openSession();
        return sqlSession;
    }

    /** 一个简单的测试 */
    public static void main(String[] args) throws SQLException {
        DataBaseConfig data = new DataBaseConfig();
        org.apache.ibatis.session.Configuration configuration = data.getSqlConfiguration(data.getTransactionFactory(),data.getBasicDataSource(data.getMysqlProperties()));
        configuration.addMapper(IUserMapper.class);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession session = data.getMySqlSqlSession(sf);
        System.out.println(session.getConnection().isClosed());

        IUserMapper userMapper = session.getMapper(IUserMapper.class);
        User user = userMapper.getUserInfoByUserId(1);

        System.out.println(user);
    }
}
