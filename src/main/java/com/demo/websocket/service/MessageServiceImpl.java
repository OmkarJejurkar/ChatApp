package com.demo.websocket.service;

import com.demo.websocket.model.MyMessage;
import com.demo.websocket.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    MessageRepository messageRepository;

    @Override
    public MyMessage saveMessage(MyMessage message) {
        MyMessage savedMessage = messageRepository.save(message);
        return savedMessage;
    }

    @Override
    public List<MyMessage> getMessageByNames(String sname, String rname) {
        return messageRepository.getMessagesByNames(sname,rname);
    }

    @Override
    public void deleteMessageById(int id) {
          messageRepository.deleteById(id); ;
    }

    @Override
    public void setReactionById(int messageId,int reactionId) {
        System.out.println("inside set reaction "+reactionId+" : "+messageId);
        messageRepository.setReactionById(messageId,reactionId);
    }
}
