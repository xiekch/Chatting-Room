package xiekch.chattingroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import xiekch.chattingroom.domain.*;
import xiekch.chattingroom.service.ChattingService;

@Controller
public class CreateUserController {
    private ChattingService chatting;

    public CreateUserController() {
        this.chatting = new ChattingService();
    }

    @PostMapping("/create/user")
    public String createUser(User user) {
        System.out.println("create user");
        try {
            if (chatting.userSignUp(user)) {
                return "redirect:/rooms";
            }
        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/";
    }
}