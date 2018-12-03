package xiekch.chattingroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomsController{
    @GetMapping("/rooms")
    public String rooms(){
        return "rooms";
    }
}