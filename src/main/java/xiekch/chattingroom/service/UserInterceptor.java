package xiekch.chattingroom.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import java.io.IOException;
import xiekch.chattingroom.domain.*;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private ChattingService chattingService;

    public UserInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {
        System.out.println("This is UserInterceptor.");
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && chattingService.isUser(user)) {
            return true;
        }
        response.sendRedirect("/");
        return false;
    }
}
