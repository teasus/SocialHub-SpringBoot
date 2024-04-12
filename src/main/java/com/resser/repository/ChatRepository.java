package com.resser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.resser.modal.Chat;
import com.resser.modal.User;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("select c from Chat c join c.users u where u.id=:userId")
    public List<Chat> findChatByUserId(@Param("userId") Long long1);

    @Query("select c from Chat c where c.isGroup=false and :user member of c.users and :reqUser member of c.users")
    public Chat findSingleChatByUserIds(@Param("user") User user, @Param("reqUser") User reqUser);

}
