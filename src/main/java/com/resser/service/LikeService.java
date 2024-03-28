package com.resser.service;

import java.util.List;

import com.resser.exception.UserException;
import com.resser.modal.Like;
import com.resser.modal.User;

public interface LikeService {
    
    public Like likeTweet(Long tweetId,User user) throws UserException,TweetException;

    public List<Like> getAllLikes(Long tweetId) throws TweetException;  
}
