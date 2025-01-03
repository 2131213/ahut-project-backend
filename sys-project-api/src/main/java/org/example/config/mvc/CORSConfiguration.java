package org.example.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfiguration implements WebMvcConfigurer {
    @Value("${web.load-path}")
    private String loadPath; // 图片访问路径

    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域访问的路径
                .allowedOriginPatterns("*") // 允许跨域访问的源
                .allowedMethods("*") // 允许跨域访问的方法
                .allowedHeaders("*") // 允许跨域访问的请求头
                .maxAge(3600) // 预检请求的缓存时间（秒），即在这个时间段
                .allowCredentials(true);//允许携带cookie
    }

    // 图片访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        System.out.println("loadPath: " + loadPath);
        registry.addResourceHandler("/images/**")
                .addResourceLocations(loadPath);
    }
}