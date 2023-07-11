package com.wuyanzu.diancan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.*;

@ComponentScan("com.wuyanzu.diancan.controller")
@EnableWebMvc    //在此处用于开启
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有接口
                .allowCredentials(true) // 是否发送 Cookie
                .allowedOriginPatterns("*") // 支持域
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"}) // 支持方法
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/food/**")
                .addResourceLocations("file:" + System.getProperty("user.dir")+"\\src\\main\\resources\\files\\");
//        registry.addResourceHandler("/food/**")
//                .addResourceLocations("file:/www/wwwroot/files");
//        .addResourceLocations("file:///"+System.getProperty("user.dir")+"/src/main/resources/files/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override

    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {

        configurer.setDefaultTimeout(20000);

        configurer.registerCallableInterceptors(timeoutInterceptor());

    }

    @Bean

    public TimeoutCallableProcessingInterceptor timeoutInterceptor() {

        return new TimeoutCallableProcessingInterceptor();

    }


}
