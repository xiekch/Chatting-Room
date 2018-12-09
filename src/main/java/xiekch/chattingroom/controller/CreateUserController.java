package xiekch.chattingroom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import xiekch.chattingroom.domain.*;
import xiekch.chattingroom.service.ChattingService;

@Controller
public class CreateUserController {
    
    @PostMapping("/create/user")
    public String createUser(User user, HttpSession session) {
        System.out.println("create user");
        try {
            if (ChattingService.getInstance().userSignIn(user)) {
                session.setAttribute("user", user);
                return "redirect:/rooms";
            }
        } catch (RuntimeException e) {
            try {
                if (ChattingService.getInstance().userSignUp(user)) {
                    session.setAttribute("user", user);;
                    return "redirect:/rooms";
                }
            } catch (RuntimeException err) {
                e.toString();
            }
        }
        return "redirect:/";
    }
}