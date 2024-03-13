package com.demo.websocket.repository;

import com.demo.websocket.model.MyMessage;
import com.demo.websocket.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface MessageRepository extends JpaRepository<MyMessage,Integer> {
    @Query(value = "select * from chat where( sender_name = :rname and reciever_name = :sname ) or (sender_name = :sname and reciever_name = :rname);",nativeQuery = true)
    List<MyMessage> getMessagesByNames(@Param("sname") String sname, @Param("rname") String rname);
    @Modifying
    @Query(value="update chat set reaction = :reactionId where id = :messageId",nativeQuery = true)
    void setReactionById(@Param("messageId") int messageId,@Param("reactionId") int reactionId);
}
