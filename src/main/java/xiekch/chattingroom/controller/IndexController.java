package xiekch.chattingroom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(HttpSession session) {
        System.out.println("index");
        if (session.getAttribute("user") == null) {
            return "index";
        } else {
            return "redirect:/rooms";
        }
    }
}
