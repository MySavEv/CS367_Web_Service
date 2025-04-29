package com.saveohm.itservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // เปิด CORS ให้กับทุก URL path โดยอนุญาตให้เชื่อมต่อจาก origin ทุกที่
        registry.addMapping("/**")
                .allowedOrigins("*") // เพิ่ม URL ที่จะอนุญาต
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // ระบุ HTTP methods ที่อนุญาต
                .allowedHeaders("*")
                .allowCredentials(false);
    }
    
}
