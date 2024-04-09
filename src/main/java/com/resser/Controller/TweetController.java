package com.resser.Controller;

import java.util.List;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

import com.resser.Dto.TweetDto;
import com.resser.Dto.mapper.TweetDtoMapper;
import com.resser.Response.ApiResponse;
import com.resser.exception.UserException;
import com.resser.modal.Tweet;
import com.resser.modal.User;
import com.resser.request.TweetReplyRequest;
import com.resser.service.TweetException;
import com.resser.service.TweetService;
import com.resser.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/api/tweets")
public class TweetController {
    
    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<TweetDto> createTweet (@RequestBody Tweet req,
    @RequestHeader("Authorization") String jwt )  throws UserException,TweetException {

        User user = userService.UserProfileByJwt(jwt);

        Tweet tweet = tweetService.createTweet(req, user);

        TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);

        return new ResponseEntity<>(tweetDto,HttpStatus.CREATED);

    }

    @PostMapping("/reply")
    public ResponseEntity<TweetDto> replyTweet (@RequestBody TweetReplyRequest req,
    @RequestHeader("Authorization") String jwt )  throws UserException,TweetException {

        User user = userService.UserProfileByJwt(jwt);

        Tweet tweet = tweetService.createReply(req, user);

        TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);

        return new ResponseEntity<>(tweetDto,HttpStatus.CREATED);

    }

    @PutMapping("/{tweetId}/retweet")
    public ResponseEntity<TweetDto> reTweet (@PathVariable Long tweetId,
    @RequestHeader("Authorization") String jwt )  throws UserException,TweetException {

        User user = userService.UserProfileByJwt(jwt);

        Tweet tweet = tweetService.reTweet(tweetId, user);

        TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);

        return new ResponseEntity<>(tweetDto,HttpStatus.CREATED);

    }

    @GetMapping("/{tweetId}")
    public ResponseEntity<TweetDto> findTweetById (@PathVariable Long tweetId,
    @RequestHeader("Authorization") String jwt )  throws UserException,TweetException {

        User user = userService.UserProfileByJwt(jwt);

        Tweet tweet = tweetService.findById(tweetId);

        TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, user);

        return new ResponseEntity<>(tweetDto,HttpStatus.ACCEPTED);

    }


    @PutMapping("/{tweetId}/deleteTweet")
    public ResponseEntity<ApiResponse> deleteTweet (@PathVariable Long tweetId,
    @RequestHeader("Authorization") String jwt )  throws UserException,TweetException {

        User user = userService.UserProfileByJwt(jwt);

        tweetService.deleteTweetById(tweetId,user.getId());

        ApiResponse response = new ApiResponse("Tweet Deleted Successfully", true);

        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);

    }


    @GetMapping("/")
    public ResponseEntity<List<TweetDto>> getAllTweet (
    @RequestHeader("Authorization") String jwt )  throws UserException,TweetException {

        List<Tweet> tweets = tweetService.findAllTweets();
        System.out.println("twtss");

        User user = userService.UserProfileByJwt(jwt);

        List<TweetDto> TweetDto = TweetDtoMapper.toTweetDtos(tweets, user);
        

        return new ResponseEntity<>(TweetDto,HttpStatus.OK);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TweetDto>> getUsersAllTweets (
    @RequestHeader("Authorization") String jwt,@PathVariable("userId") Long userId )  throws UserException,TweetException {

        
        User user = userService.findUserById(userId);
        User authUser = userService.UserProfileByJwt(jwt);
        List<Tweet> tweets = tweetService.getUserTweet(user);
        List<TweetDto> TweetDto = TweetDtoMapper.toTweetDtos(tweets, user);
    
        return new ResponseEntity<>(TweetDto,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/likes")
    public ResponseEntity<List<TweetDto>> getUserLikedTweet (
    @RequestHeader("Authorization") String jwt,@PathVariable("userId") Long userId )  throws UserException,TweetException {

        
        User user = userService.findUserById(userId);
        User authUser = userService.UserProfileByJwt(jwt);
        List<Tweet> tweets = tweetService.getByLikesContainsUser(user);
        List<TweetDto> TweetDto = TweetDtoMapper.toTweetDtos(tweets, user);
    
        return new ResponseEntity<>(TweetDto,HttpStatus.OK);
    }
    

}
