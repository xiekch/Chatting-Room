package xiekch.chattingroom.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "<h1>Hello</h1>";
    }
}
