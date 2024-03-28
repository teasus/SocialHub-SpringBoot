package com.resser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.resser.exception.UserException;
import com.resser.modal.Tweet;
import com.resser.modal.User;
import com.resser.request.TweetReplyRequest;

@Service
public interface TweetService {

    public Tweet createTweet(Tweet req,User user) throws UserException;
    
    public List<Tweet> findAllTweets() throws UserException;
    
    public Tweet reTweet(Long tweetId,User user) throws UserException,TweetException;


    public Tweet findById(Long tweetId) throws TweetException;

    //to delete the tweet
    public void deleteTweetById(Long tweetId,Long userId) throws TweetException,UserException;

    // remove the retweet 
    public Tweet removeFromRetweet(Long tweetId,User user) throws TweetException,UserException;

    public Tweet createReply(TweetReplyRequest req,User user) throws TweetException;


    public List<Tweet> getUserTweet(User user) throws UserException;

    //get the list of  tweet liked by user
    public List<Tweet> getByLikesContainsUser(User user) throws UserException;
}
