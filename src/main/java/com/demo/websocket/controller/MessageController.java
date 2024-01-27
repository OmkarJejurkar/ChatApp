package com.demo.websocket.controller;

import com.demo.websocket.model.MyMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class MessageController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    MessageController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/receive")
    public MyMessage sendMessage(MyMessage myMessage){
        System.out.println(myMessage.getContent());
        System.out.println(myMessage.getHr());
        System.out.println(myMessage.getMin());
        return myMessage;
    }

    @MessageMapping("/sendMessage/{userId}")
//    @SendTo("/topic/receive/1")
    public void sendPersonalMessage(@DestinationVariable int userId, MyMessage myMessage){
        System.out.println(myMessage.getContent());
        System.out.println(myMessage.getHr());
        System.out.println(myMessage.getMin());
        String userDestination = "/topic/receive/"+userId;
        simpMessagingTemplate.convertAndSend(userDestination, myMessage);
//        return myMessage;
    }
}
