package com.demo.websocket.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity(name = "chat")
public class MyMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "reciever_name")
    private String recieverName;
    private String content;
    private int hr;
    private int min;
    private int reaction;
}
