package com.resser.service;

import java.util.List;

import com.resser.exception.UserException;
import com.resser.modal.Chat;
import com.resser.modal.User;




public interface ChatService {

    public Chat createChat(User reqUser, Long userId) throws UserException;

    public Chat findChatById(Long chatId) throws UserException;

    public List<Chat> findAllChatByUserId(Long userId) throws UserException;

    //public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException;

    //public Chat addUserToGroup(Integer userId, Integer chatId, User reqUser) throws UserException, ChatException;

   // public Chat renameGroup(Integer chatId, String groupName, User reqUser) throws ChatException, UserException;

    //public Chat removeFromGroup(Integer chatId, Integer userId, User reqUser) throws UserException, ChatException;

    public void deleteChat(Long chatId, Long userId) throws UserException;

}