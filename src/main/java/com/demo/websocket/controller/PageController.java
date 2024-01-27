package com.demo.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/personal")
    public String getIndex1(){
        return "index1.html";
    }
    @GetMapping("/login")
    public String getLoginPage(){
        return "html/login.html";
    }
}
