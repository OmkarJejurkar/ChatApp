package com.demo.websocket.repository;

import com.demo.websocket.model.MyMessage;
import com.demo.websocket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MyMessage,Integer> {
    @Query(value = "select * from chat where( sender_name = :rname and reciever_name = :sname ) or (sender_name = :sname and reciever_name = :rname);",nativeQuery = true)
    List<MyMessage> getMessagesByNames(@Param("sname") String sname, @Param("rname") String rname);
}
