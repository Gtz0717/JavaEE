package com.test_e.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.ViewResolver;

import javax.sql.DataSource;

// 合并两个配置类，简化配置
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@MapperScan("com.test_e.dao")
@PropertySource("classpath:application.properties")
@ComponentScan("com.test_e")
public class AppConfig implements WebMvcConfigurer {

    // 数据源配置 - 添加连接池配置
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/course_selection_system?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("0717Gtz@");

        // 连接池配置
        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(5);
        dataSource.setConnectionTimeout(30000);
        return dataSource;
    }

    // MyBatis SqlSessionFactory
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());

        // 设置MyBatis配置
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true); // 开启驼峰命名转换
        factoryBean.setConfiguration(configuration);

        // 配置类型别名
        factoryBean.setTypeAliases(new Class<?>[] {
                com.test_e.entity.Course.class,
                com.test_e.entity.Student.class,
                com.test_e.entity.Teacher.class,
                com.test_e.entity.Teaching_class.class,
                com.test_e.entity.User.class,
                com.test_e.entity.Selection.class
        });

         //设置映射文件位置
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return factoryBean.getObject();
    }
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.test_e.dao");
        return configurer;
    }

    // 事务管理器
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    // 视图解析器
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    // 静态资源处理
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 使用 BCrypt 加密
    }


}