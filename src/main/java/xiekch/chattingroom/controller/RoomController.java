package xiekch.chattingroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomController{

    @GetMapping("/room")
    public String room(){
        return "room";
    }
}