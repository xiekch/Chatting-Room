package xiekch.chattingroom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import xiekch.chattingroom.service.*;
import xiekch.chattingroom.domain.*;

@Controller
public class CreateRoomController {
    private ChattingService chatting;

    public CreateRoomController() {
        this.chatting = new ChattingService();
    }

    @PostMapping("/create/room")
    public String createRoom(@RequestParam("name") String name, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        System.out.println("create room");
        Room room = new Room(name);
        try {
            if (chatting.roomSignUp(room, (User) session.getAttribute("user"))) {
                return "redirect:/room?name=" + room.getName();
            }
        } catch (RuntimeException e) {
            // System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
