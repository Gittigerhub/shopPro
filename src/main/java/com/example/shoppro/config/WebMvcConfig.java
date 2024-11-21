package com.example.shoppro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}") // file:///C:/shop/
    String uploadPath;      // itemImgLocation = file:///C:/shop/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")   // /images/** 경로로 요청한 이미지를
                .addResourceLocations(uploadPath);      // 가져오는 경로는 uploadPath다.
    }
}
