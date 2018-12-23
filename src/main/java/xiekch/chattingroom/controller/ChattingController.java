package xiekch.chattingroom.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
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

   
}
