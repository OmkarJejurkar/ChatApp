package com.demo.websocket.controller;

import com.demo.websocket.model.MyMessage;
import com.demo.websocket.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class MessageController {
    @Autowired
    MessageService messageService;
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


    @PostMapping("/saveMessage")
    public ResponseEntity<Object> saveMessage(@RequestBody MyMessage myMessage){
        System.out.println(myMessage.toString());
        MyMessage msg = messageService.saveMessage(myMessage);
        if(msg!=null){
        return ResponseEntity.status(HttpStatus.OK).body(myMessage);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message not saved ");
        }
    }

    @PostMapping("/getMessages")
    public ResponseEntity<Object> getMessages(@RequestBody MyMessage myMessage){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessageByNames(myMessage.getSenderName(),myMessage.getRecieverName()));
    }
}
