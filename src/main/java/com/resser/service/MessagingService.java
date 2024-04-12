package com.resser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.resser.exception.UserException;
import com.resser.modal.Chat;
import com.resser.modal.Message;
import com.resser.modal.User;

@Service
public interface MessagingService {

    public List<Message> findByChatId(Long userId) throws UserException;

    public Message createChatMessage(Message message, User user, Chat chat) throws UserException;

}
