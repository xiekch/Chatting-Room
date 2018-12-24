package xiekch.chattingroom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xiekch.chattingroom.domain.*;
import xiekch.chattingroom.service.ChattingService;

@Controller
public class ChangeRoomController {
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/create/room")
    public String createRoom(@RequestParam("name") String name, HttpSession session) {
        System.out.println("create room");
        Room room = new Room(name);
        try {
            if (ChattingService.getInstance().roomSignUp(room, (User) session.getAttribute("user"))) {
                this.template.convertAndSend("/rooms/", "{\"create\": \"" + room.getName() + "\"}");
                return "redirect:/rooms";
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("change/room/quit")
    public String quitRoom(@RequestBody String request, HttpSession session, Model model) {
        // deal with the request data posted by form
        String roomName = request.substring(0, request.indexOf('='));
        User user = (User) session.getAttribute("user");
        Room room = ChattingService.getInstance().getRoom(roomName);
        ChattingService.getInstance().userQuitRoom(room, user);
        if (!ChattingService.getInstance().isRoom(roomName)) {
            this.template.convertAndSend("/rooms/", "{\"delete\": \"" + roomName + "\"}");
        }

        return "redirect:/rooms";
    }

    @PostMapping("change/room/join")
    public String joinRoom(@RequestBody String request, HttpSession session, Model model) {
        String roomName = request.substring(0, request.indexOf('='));
        User user = (User) session.getAttribute("user");
        try {
            Room room = ChattingService.getInstance().getRoom(roomName);
            ChattingService.getInstance().userEnterRoom(room, user);
        } catch (Exception e) {
            System.out.println("room doesn't exist");
        }
        return "redirect:/rooms";
    }
}