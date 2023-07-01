package com.example.sysu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /* 设置允许跨域访问的路径 */
        /* 允许访问IP:port的所有路径，如IP:port/login，IP:port/register等等 */
        CorsRegistration cors = registry.addMapping("/**");

        /* 设置允许的跨域访问的来源，设置任何来源都可 */
        cors.allowedOrigins("*");

        /* 设置允许的请求头，任意请求头都可 */
        cors.allowedHeaders("*");



        /* 设置允许的HTTP请求，GET和POST */
        cors.allowedMethods("GET", "POST");

        /* 设置请求的有效期，单位秒，1小时 */
        cors.maxAge(3600);


    }
}
