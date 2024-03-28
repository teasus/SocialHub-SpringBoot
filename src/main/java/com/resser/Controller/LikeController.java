package com.resser.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resser.Dto.LikeDto;
import com.resser.Dto.TweetDto;
import com.resser.Dto.mapper.LikeDtoMapper;
import com.resser.Dto.mapper.TweetDtoMapper;
import com.resser.exception.UserException;
import com.resser.modal.Like;
import com.resser.modal.Tweet;
import com.resser.modal.User;
import com.resser.service.LikeService;
import com.resser.service.TweetException;
import com.resser.service.TweetService;
import com.resser.service.UserService;

@RestController
@RequestMapping("/api")
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;

    @PostMapping("/{tweetId}/likes")
    public ResponseEntity<LikeDto> likeTweet(@PathVariable Long tweetId,
            @RequestHeader("Authorization") String jwt) throws UserException, TweetException {

        User user = userService.UserProfileByJwt(jwt);

        Like like = likeService.likeTweet(tweetId, user);
        LikeDto likeDto = LikeDtoMapper.toLikeDtos(like, user);

        return new ResponseEntity<LikeDto>(likeDto, HttpStatus.CREATED);

    }

    @GetMapping("/{tweetId}/allLikes")
    public ResponseEntity<List<LikeDto>> getAllLikesOfTweet(@PathVariable Long tweetId,
            @RequestHeader("Authorization") String jwt) throws UserException, TweetException {

        User user = userService.UserProfileByJwt(jwt);
        List<Like> like = likeService.getAllLikes(tweetId);

       List<LikeDto> likeDto = LikeDtoMapper.toLikeDtos(like, user);

        return new ResponseEntity<>(likeDto, HttpStatus.CREATED);

    }

}
