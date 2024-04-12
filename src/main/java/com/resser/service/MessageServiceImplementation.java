package com.resser.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resser.exception.UserException;
import com.resser.modal.Chat;
import com.resser.modal.Message;
import com.resser.modal.User;
import com.resser.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessagingService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message createChatMessage(Message message, User user, Chat chat) throws UserException {

        Message newChat = new Message();
        newChat.setContent(message.getContent());
        newChat.setChat(chat);
        newChat.setUser(user);

        newChat.setCreatedAt(LocalDateTime.now());
        newChat.setImage(message.getImage());
        if (message.getImage() != null) {

            newChat.setMultiMedia(true);
        } else {
            newChat.setMultiMedia(false);
        }

        return messageRepository.save(newChat);
    }

    @Override
    public List<Message> findByChatId(Long userId) throws UserException {

        return messageRepository.findByChatId(userId);
    }

}
