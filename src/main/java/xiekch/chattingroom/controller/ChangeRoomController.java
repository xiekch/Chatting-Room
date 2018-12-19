package xiekch.chattingroom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xiekch.chattingroom.domain.*;
import xiekch.chattingroom.service.ChattingService;

@Controller
public class ChangeRoomController {

    // @PostMapping("change/room/enter")
    // public String enterRoom(@RequestParam("roomName") String roomName, HttpSession session, Model model) {
    //     // Room room = ChattingService.getInstance().getRoom(roomName);
    //     // User user = (User) session.getAttribute("user");
    //     // ChattingService.getInstance().userEnterRoom(room, user);

    //     return "redirect:/room?roomName=" + roomName;
    // }

    @PostMapping("change/room/quit")
    public String quitRoom(@RequestBody String request, HttpSession session, Model model) {
        //deal with the request data posted by form
        String roomName = request.substring(0, request.indexOf('='));
        Room room = ChattingService.getInstance().getRoom(roomName);
        User user = (User) session.getAttribute("user");
        ChattingService.getInstance().userQuitRoom(room, user);

        return "redirect:/rooms";
    }

    @PostMapping("change/room/join")
    public String joinRoom(@RequestBody String request, HttpSession session, Model model) {
        String roomName = request.substring(0, request.indexOf('='));
        Room room = ChattingService.getInstance().getRoom(roomName);
        User user = (User) session.getAttribute("user");
        ChattingService.getInstance().userEnterRoom(room, user);

        return "redirect:/rooms";
    }
}