package com.zhongwang.empl.product.webapp.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description: Druid数据库连接池监控配置信息：访问地址 http://ip:端口/druid
 * @author: slliver
 * @date: 2019/9/3 13:41
 * @version: 1.0
 */
@Configuration
public class DruidConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);

    // 引用application-**.yml里的配置信息
    private static final String DB_PREFIX = "spring.datasource";

    @Bean
    public ServletRegistrationBean druidServlet() {
        logger.info("init Druid Servlet Configuration ");
        // 创建servlet注册实体
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP白名单
        servletRegistrationBean.addInitParameter("allow", "*");
        // IP黑名单(共同存在时，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny", "192.168.11.99");
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "druid");
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 过滤器
     */
    @Bean
    public FilterRegistrationBean statFilter(){
        // 创建过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        // 以下配置暂时用不到
        /**
         filterRegistrationBean.addInitParameter("profileEnable", "true");
         filterRegistrationBean.addInitParameter("principalCookieName","USER_COOKIE");
         filterRegistrationBean.addInitParameter("principalSessionName","USER_SESSION");
         filterRegistrationBean.addInitParameter("DruidWebStatFilter","/*");
         **/
        return filterRegistrationBean;
    }

    // 解决 spring.datasource.filters=stat,wall,log4j 无法正常注册进去
    @ConfigurationProperties(prefix = DB_PREFIX)
    @Data
    class IDataSourceProperties {
        private String url;
        private String username;
        private String password;
        private String driverClassName;
        private int initialSize;
        private int minIdle;
        private int maxActive;
        private int maxWait;
        private int timeBetweenEvictionRunsMillis;
        private int minEvictableIdleTimeMillis;
        private String validationQuery;
        private boolean testWhileIdle;
        private boolean testOnBorrow;
        private boolean testOnReturn;
        private boolean poolPreparedStatements;
        private int maxPoolPreparedStatementPerConnectionSize;
        private String filters;
        private String connectionProperties;


        @Bean(initMethod = "init", destroyMethod = "close")    //声明其为Bean实例
        @Primary  //在同样的DataSource中，首先使用被标注的DataSource
        public DataSource dataSource() {
            DruidDataSource datasource = new DruidDataSource();
            datasource.setUrl(url);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);

            //configuration
            datasource.setInitialSize(initialSize);
            datasource.setMinIdle(minIdle);
            datasource.setMaxActive(maxActive);
            datasource.setMaxWait(maxWait);
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            datasource.setValidationQuery(validationQuery);
            datasource.setTestWhileIdle(testWhileIdle);
            datasource.setTestOnBorrow(testOnBorrow);
            datasource.setTestOnReturn(testOnReturn);
            datasource.setPoolPreparedStatements(poolPreparedStatements);
            datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
            try {
                datasource.setFilters(filters);
            } catch (SQLException e) {
                System.err.println("druid configuration initialization filter: " + e);
            }
            datasource.setConnectionProperties(connectionProperties);
            return datasource;
        }
    }
}
