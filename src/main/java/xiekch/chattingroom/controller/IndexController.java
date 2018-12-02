package xiekch.chattingroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/")
public class IndexController
{
    @GetMapping("/")
    public String index(){
        System.out.println("index");
        return "index";
    }
}
