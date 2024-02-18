package com.demo.websocket.service;

import com.demo.websocket.model.MyMessage;

import java.util.List;

public interface MessageService {
    public MyMessage saveMessage(MyMessage message);
    public List<MyMessage> getMessageByNames(String sname, String rname);
}
