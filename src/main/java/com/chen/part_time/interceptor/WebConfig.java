package com.chen.part_time.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author 陈奕成
 * @create 2020 05 10 15:21
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/merchant/**")
                .excludePathPatterns("/merchant")
                .excludePathPatterns("/merchant/login").excludePathPatterns("/static/**")
        .excludePathPatterns("/merchant/static/**");

        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login").excludePathPatterns("/static/**")
                .excludePathPatterns("/admin/static/**");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
