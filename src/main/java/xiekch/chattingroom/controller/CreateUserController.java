package xiekch.chattingroom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import xiekch.chattingroom.domain.*;
import xiekch.chattingroom.service.ChattingService;

@Controller
public class CreateUserController {
    private final static int interval = 60 * 60 * 4;

    @Autowired
    private ChattingService chattingService;

    @PostMapping("/")
    public String createUser(User user, HttpSession session, Model model) {
        System.out.println("create user");
        try {
            if (chattingService.userSignUp(user)) {
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(interval);
                return "redirect:/rooms";
            }
        } catch (RuntimeException e) {
            try {
                if (chattingService.userSignIn(user)) {
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(interval);
                    return "redirect:/rooms";
                }
            } catch (RuntimeException err) {
                err.printStackTrace();
                model.addAttribute("error", err.toString().split(":")[1]);
                return "index"; // "{\"error\": \"" + err.toString().split(":")[1] + "\"}";
            }
        }
        return "redirect:/";
    }
}