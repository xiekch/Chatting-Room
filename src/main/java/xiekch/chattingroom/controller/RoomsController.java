package xiekch.chattingroom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xiekch.chattingroom.domain.*;
import xiekch.chattingroom.service.ChattingService;

@Controller
public class RoomsController {

    @GetMapping("/rooms")
    public String rooms(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("participatedRooms", ChattingService.getInstance().getParticipatedRooms(user));
        model.addAttribute("restRooms",ChattingService.getInstance().getRestRooms(user));
        model.addAttribute("user", user);
        return "rooms";
    }
}