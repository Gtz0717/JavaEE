package com.test_e.config;



import com.test_e.config.AppConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;



public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        // 创建根应用上下文
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);

        // 添加ContextLoaderListener
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // 创建DispatcherServlet应用上下文
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.setParent(rootContext);

        // 配置DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");

        // 设置编码过滤器
        jakarta.servlet.FilterRegistration.Dynamic characterEncodingFilter =
                servletContext.addFilter("characterEncodingFilter", new org.springframework.web.filter.CharacterEncodingFilter());
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
        characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");

    }
}