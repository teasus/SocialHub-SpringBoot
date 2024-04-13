package com.resser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resser.exception.UserException;
import com.resser.modal.Chat;
import com.resser.modal.User;
import com.resser.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService {

    @Autowired
    private UserServiceImplementation userService;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, Long userId) throws UserException {

        User user = this.userService.findUserById(userId);

        Chat isChatExist = this.chatRepository.findSingleChatByUserIds(user, reqUser);

        System.out.println(isChatExist);
        if (isChatExist != null) {
            return isChatExist;
        }

        Chat chat = new Chat();
        chat.setCreatedBy(reqUser);
        chat.setChatImage(user.getImage());
        chat.setChatName(user.getFullName());
        chat.getUsers().add(user);
        chat.getUsers().add(reqUser);
        chat.setGroup(false);

        chat = this.chatRepository.save(chat);

        return chat;
    }

    @Override
    public Chat findChatById(Long chatId) throws UserException {
        return this.chatRepository.findById(chatId)
                .orElseThrow(() -> new UserException("The requested chat is not found"));
    }

    @Override
    public List<Chat> findAllChatByUserId(Long userId) throws UserException {
        User user = this.userService.findUserById(userId);

        List<Chat> chats = this.chatRepository.findChatByUserId(user.getId());

        return chats;
    }

      
     

    
    

    

    @Override
    public void deleteChat(Long chatId, Long userId) throws UserException {
        Chat chat = this.chatRepository.findById(chatId)
                .orElseThrow(() -> new UserException("The expected chat is not found while deleteing"));
        this.chatRepository.delete(chat);
    }

}