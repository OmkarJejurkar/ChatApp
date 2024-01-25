package com.demo.websocket.repository;

import com.demo.websocket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select * from user where user_name = :uname and password = :password ",nativeQuery = true)
    User getAuthenticatedUser(@Param("uname") String uname,@Param("password") String password);
}
