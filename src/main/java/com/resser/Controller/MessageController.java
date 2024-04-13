package com.resser.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resser.modal.Chat;
import com.resser.modal.Message;
import com.resser.modal.User;
import com.resser.repository.ChatRepository;
import com.resser.repository.MessageRepository;
import com.resser.service.ChatService;
import com.resser.service.MessagingService;
import com.resser.service.UserService;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    // @Autowired
    // SimpMessagingTemplate template;

    @Autowired
    private MessagingService messagingService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatService chatService;

    @MessageMapping("/message")
    @SendTo("/group/public")
    public ResponseEntity<Message> recieveMessage(@Payload Message message)
            throws Exception {
        System.out.println("mentall ");
        simpMessagingTemplate.convertAndSend("/group/" + message.getChat().getId().toString(), message);
        Thread.sleep(1000);
        Message newMessage = new Message();
        newMessage.setContent(message.getContent());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @MessageMapping("/chat")
    // @SendTo("/group/public")
    // public ResponseEntity<Message> recieveAllMessage(@Payload Message message)
    // throws Exception {
    // simpMessagingTemplate.convertAndSend("/group/"+message.getId().toString(),message);
    // Thread.sleep(1000);
    // Message newMessage = new Message();
    // newMessage.setContent(message.getContent());
    // return new ResponseEntity<>(HttpStatus.OK);
    // }

    @PostMapping("/send/{chatId}")
    public ResponseEntity<Message> createMessage(@RequestBody Message message, @PathVariable("chatId") Long chatId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.UserProfileByJwt(jwt);
        Chat chat = chatService.findChatById(chatId);

        Message add = messagingService.createChatMessage(message, user, chat);

        // Tweet tweet = tweetService.createTweet(req, user);

        // TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);
        return new ResponseEntity<Message>(add, HttpStatus.CREATED);
        // return new ResponseEntity<>(tweetDto, HttpStatus.CREATED);

    }

    @GetMapping("/ChatMessages/{chatId}")
    public ResponseEntity<List<Message>> getAllMessagesOfChat(@PathVariable("chatId") Long chatId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.UserProfileByJwt(jwt);
        List<Message> messages = messagingService.findByChatId(chatId);
        return new ResponseEntity<>(messages,
                HttpStatus.OK);
        // return new ResponseEntity<>(tweetDto, HttpStatus.CREATED);

    }
}
