package xiekch.chattingroom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xiekch.chattingroom.domain.*;
import xiekch.chattingroom.service.ChattingService;


@Controller
public class ChangeRoomController{

    @PostMapping("change/room")
    public String changeroom(@RequestParam("roomName")){

    }
}