package xiekch.chattingroom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import xiekch.chattingroom.service.ChattingService;
import xiekch.chattingroom.domain.*;

@Controller
public class RoomController {

    @GetMapping("/room")
    public String room(@RequestParam("roomName") String roomName, @RequestParam("user") String userName, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        try {
            if (!ChattingService.getInstance().isRoomUser(roomName, user)) {
                return "redirect:/rooms";
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "redirect:/rooms";
        }
        model.addAttribute("room", ChattingService.getInstance().getRoom(roomName));
        model.addAttribute("userName", userName);
        return "room";
    }
}