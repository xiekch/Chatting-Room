package xiekch.chattingroom.controller;

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
    public String createRoom(@RequestParam("name") String name) {
        System.out.println("create room");
        Room room = new Room(name);
        try {
            if (chatting.roomSignUp(room)) {
                return "redirect:/room?name=" + room.getName();
            }
        } catch (RuntimeException e) {
            // System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
