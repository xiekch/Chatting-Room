package xiekch.chattingroom.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import xiekch.chattingroom.domain.*;
import xiekch.chattingroom.service.ChattingService;

@Controller
public class ChattingController{
    @Autowired
    private SimpMessagingTemplate template;

     @MessageMapping("/{roomName}")
    public void userChat(String message, @DestinationVariable String roomName){
        System.out.println(message);
        this.template.convertAndSend("/userChat/" + roomName, message);
    }

}
