package xiekch.chattingroom.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import java.io.IOException;

public class UserInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)throws IOException{
        System.out.println("This is interceptor.");
        if(request.getSession().getAttribute("user")!=null){
            return true;
        }
        response.sendRedirect("/");
        return false;
    }
}
