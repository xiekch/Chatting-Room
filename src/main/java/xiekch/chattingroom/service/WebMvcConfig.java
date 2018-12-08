package xiekch.chattingroom.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/rooms");
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/room");
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/create/room");
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/logOut");
    }
}
