package xiekch.chattingroom.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.beans.factory.annotation.Autowired;

import xiekch.chattingroom.domain.Message;
import xiekch.chattingroom.service.ChattingService;

@Controller
public class ChattingController {
    @Autowired
    private ChattingService chattingService;

    @Autowired
    private SimpMessagingTemplate template;

    @ResponseBody
    @MessageMapping("/room/{roomName}")
    public void room(@RequestBody Message message, @DestinationVariable String roomName) {
        message.escape();
        chattingService.userSpeak(message.getUserName(), message.getRoomName(), message.getMessage(),
                message.getDate());

        template.convertAndSend("/room/" + roomName, message);
    }

}
