package com.resser.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resser.exception.UserException;
import com.resser.modal.Chat;
import com.resser.modal.User;
import com.resser.service.ChatService;
import com.resser.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    
    @Autowired
    private UserService userService;


    @GetMapping("/All")
    public ResponseEntity<List<Chat>> getAllChatsOfUser(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.UserProfileByJwt(jwt);

        List<Chat> chats = chatService.findAllChatByUserId(user.getId());

        return new ResponseEntity<>(chats,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable("id") Long id, @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.UserProfileByJwt(jwt);

        Chat chats = chatService.findChatById(id);

        return new ResponseEntity<>(chats,HttpStatus.OK);
    }

    @PostMapping("/NewChat/{userId}")
    public ResponseEntity<Chat> createNewChat(@RequestHeader("Authorization") String jwt,@PathVariable("userId")Long chatUser) throws UserException {
        User user = userService.UserProfileByJwt(jwt);

        Chat chats = chatService.createChat(user, chatUser);

        return new ResponseEntity<Chat>(chats,HttpStatus.OK);
    }
    
    
}
