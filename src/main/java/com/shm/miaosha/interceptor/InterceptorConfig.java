//package com.shm.miaosha.interceptor;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.util.Arrays;
//
///**
// * @Auther: shm
// * @Date: 2019/6/4
// * @Description: com.shm.miaosha.interceptor
// * @version: 1.0
// */
//@Configuration
//public class InterceptorConfig extends WebMvcConfigurationSupport {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 多个拦截器组成一个拦截器链
//        // addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(new UserInterceptor()).excludePathPatterns(Arrays.asList("/js/**","/bootstrap/**","/jquery-validation/**","/layer/**","/templates/**")).addPathPatterns("/miaosha/**");
//        super.addInterceptors(registry);
//    }
//}
