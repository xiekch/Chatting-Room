package xiekch.chattingroom.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xiekch.chattingroom.domain.*;
import xiekch.chattingroom.service.ChattingService;

@ServerEndpoint("/websocket")
public class ChattingWebSocket{

    @PostMapping("chatting")
    public String chatting(P){

    }
}