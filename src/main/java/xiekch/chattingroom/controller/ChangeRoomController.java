package xiekch.chattingroom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xiekch.chattingroom.domain.*;
import xiekch.chattingroom.service.ChattingService;

@Controller
public class ChangeRoomController {

    @PostMapping("change/room/enter")
    public String enterRoom(@RequestParam("roomName") String roomName, HttpSession session, Model model) {
        // Room room = ChattingService.getInstance().getRoom(roomName);
        // User user = (User) session.getAttribute("user");
        // ChattingService.getInstance().userEnterRoom(room, user);

        return "redirect:/room?roomName=" + roomName;
    }

    @PostMapping("change/room/quit")
    public String quitRoom(@RequestParam("roomName") String roomName, HttpSession session, Model model) {
        Room room = ChattingService.getInstance().getRoom(roomName);
        User user = (User) session.getAttribute("user");
        ChattingService.getInstance().userQuitRoom(room, user);
        
        return "redirect:/rooms";
    }

    @PostMapping("change/room/join")
    public String joinRoom(@RequestParam("roomName") String roomName, HttpSession session, Model model) {
        Room room = ChattingService.getInstance().getRoom(roomName);
        User user = (User) session.getAttribute("user");
        ChattingService.getInstance().userEnterRoom(room, user);

        return "redirect:/rooms";
    }
}