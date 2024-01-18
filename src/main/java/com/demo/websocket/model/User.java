package com.demo.websocket.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String name;
    private String password;
    @Column(name="registeredTime")
    private Date registeredTime;
    @Column(name="lastLoginTime")
    private Date lastLoginTime;
}
