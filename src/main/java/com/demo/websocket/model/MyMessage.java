package com.demo.websocket.model;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MyMessage {
    private String senderName;
    private String content;
    private int hr;
    private int min;
}
