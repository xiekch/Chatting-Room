package xiekch.chattingroom.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import xiekch.chattingroom.domain.*;
import xiekch.chattingroom.service.ChattingService;

@Controller
public class ChattingController{
    @Autowired
    private SimpMessagingTemplate template;

     @MessageMapping("/userChat")
    public void userChat(String message){//此处应修改 //@PathVariable("roomName")String roomName,
        System.out.println(message);
        this.template.convertAndSend("/userChat/wechat",message);
    }

}
