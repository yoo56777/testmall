package com.testmall;

import com.testmall.Interceptor.ManLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    ManLoginInterceptor manLoginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //全部允許，包含跨來源要求
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //在這個patterns裡面就是不用登入的
//        List<String> patterns = new ArrayList<>();
//        patterns.add("/manager/Login");
//
//        registry.addInterceptor(manLoginInterceptor)
//                .addPathPatterns("/manager/**")
//                .addPathPatterns("/back/**")
//                .excludePathPatterns(patterns);
//    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        for(HttpMessageConverter<?> converter : converters){
            if (converter instanceof StringHttpMessageConverter){
                ((StringHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
            }
            if (converter instanceof MappingJackson2HttpMessageConverter){
                ((MappingJackson2HttpMessageConverter) converter).setDefaultCharset((StandardCharsets.UTF_8));
            }
        }
    }
}