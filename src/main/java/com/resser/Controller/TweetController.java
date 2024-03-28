package com.resser.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

import com.resser.service.TweetService;
import com.resser.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/tweets")
public class TweetController {
    
    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;

    // @PostMapping("/create")
    // public ResponseEntity<TweetDto> createTweet {



    //     return null;
    // }
    

}
