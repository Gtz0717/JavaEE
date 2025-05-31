package com.test_e.config;

//import com.test_e.interceptor.AuthInterceptor;
import com.test_e.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc   // 启用 Spring MVC
@ComponentScan("com.test_e.controller") // 扫描 Controller
public class WebMvcConfig implements WebMvcConfigurer {



    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        registry.jsp("/WEB-INF/views/", ".jsp");
    }
//    // 静态资源处理
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")          // 拦截所有路径
                .excludePathPatterns(            // 排除以下路径
                        "/login",                // 登录页
                        "/user/login",          // 登录接口
                        "/css/**",              // 静态资源
                        "/js/**",
                        "/images/**"
                );
    }
}