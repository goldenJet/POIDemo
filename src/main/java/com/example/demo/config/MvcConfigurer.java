package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义资源映射
 */
@Configuration
public class MvcConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 配置静态资源访问
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将资源路径映射到指定的classpath
        registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/");
        // 将资源路径映射到计算机本地 D 盘
//        registry.addResourceHandler("/d/**").addResourceLocations("file:D:/");
        super.addResourceHandlers(registry);
    }

}