package xiekch.chattingroom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import xiekch.chattingroom.service.*;
import xiekch.chattingroom.domain.*;

@Controller
public class CreateRoomController {
    @PostMapping("/create/room")
    public String createRoom(@RequestParam("name") String name, HttpSession session) {
        System.out.println("create room");
        Room room = new Room(name);
        try {
            if (ChattingService.getInstance().roomSignUp(room, (User) session.getAttribute("user"))) {
                return "redirect:/rooms";
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
