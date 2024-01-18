package com.demo.websocket.model;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
public class User {
    private String uname;
    private String password;
}
