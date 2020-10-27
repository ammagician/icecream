package com.apang.icecream.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DataSourceConfig {
    @Autowired
    private DataSourceProperty dataSourceProperty;


    @Bean(initMethod="init",destroyMethod="close")
    public DruidDataSource dataSource() throws SQLException {
    	DruidDataSource bds = new DruidDataSource();

		WallConfig wc = new WallConfig();
		wc.setMultiStatementAllow(true);
		WallFilter wf = new WallFilter();
		wf.setConfig(wc);
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(wf);
		bds.setProxyFilters(filters);

        bds.setDriverClassName(dataSourceProperty.getDriverClass());
        bds.setUrl(dataSourceProperty.getUrl());
        bds.setUsername(dataSourceProperty.getUsername());
        bds.setPassword(dataSourceProperty.getPassword());
        bds.setMaxActive(dataSourceProperty.getMaxPoolSize());
        bds.setInitialSize(dataSourceProperty.getInitialPoolSize());
        bds.setMaxWait(60000);
        bds.setMinIdle(dataSourceProperty.getMinPoolSize());
        bds.setValidationQuery("SELECT 1 FROM DUAL");
        bds.setTestOnBorrow(false);
        bds.setTestWhileIdle(true);
        bds.setPoolPreparedStatements(true);
        bds.setFilters("stat,log4j2,wall");    //#配置监控统计拦截的filters，stat:监控统计、log4j2：日志记录、wall：防御sql注入
        bds.setMaxPoolPreparedStatementPerConnectionSize(dataSourceProperty.getMaxPoolSize());

        return bds;
    }


    @Bean
    public SqlSessionFactory sessionFactory() throws Exception {
    	MybatisSqlSessionFactoryBean lsfb = new MybatisSqlSessionFactoryBean();
        lsfb.setDataSource(this.dataSource());
        lsfb.setConfigLocation(new ClassPathResource("config/mybatis-config.xml"));
		lsfb.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:mapper/*.xml"));
		lsfb.setPlugins(new Interceptor[] { paginationInterceptor() });
        return lsfb.getObject();
    }

    @Bean
    static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
		msc.setBasePackage("com.apang.icecream.**.mapper");
        msc.setMarkerInterface(com.baomidou.mybatisplus.core.mapper.BaseMapper.class);
        //msc.setAnnotationClass(org.springframework.stereotype.Repository.class);
        msc.setSqlSessionFactoryBeanName("sessionFactory");
        return msc;
    }


    @Bean
	public PlatformTransactionManager transactionManager() throws SQLException {
        DataSourceTransactionManager dstm = new DataSourceTransactionManager();
        dstm.setDataSource(this.dataSource());
        return dstm;
    }
    
    
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(1000); // 最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制
		paginationInterceptor.setDialectType(dataSourceProperty.getDialect());
        return paginationInterceptor;
    }
}
