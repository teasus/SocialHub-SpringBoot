package com.resser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resser.exception.UserException;
import com.resser.modal.Like;
import com.resser.modal.Tweet;
import com.resser.modal.User;
import com.resser.repository.LikeRepository;
import com.resser.repository.TweetRepository;

@Service
public class LikeServiceImplementation implements LikeService  {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private TweetService tweetService;
    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public Like likeTweet(Long tweetId, User user) throws UserException, TweetException {
        Like like =likeRepository.isLikeExist(user.getId(), tweetId);
        if(like!=null){
            likeRepository.deleteById(like.getId());
            return like; 
        }
        Tweet tweet = tweetService.findById(tweetId);
        
        Like newLike = new Like();
        newLike.setTweet(tweet);
        newLike.setUser(user);

        Like savedLike =  likeRepository.save(newLike);
        tweet.getLikes().add(savedLike);
        tweetRepository.save(tweet);

        return savedLike;
    }

    @Override
    public List<Like> getAllLikes(Long tweetId) throws TweetException {
        
        Tweet tweet = tweetService.findById(tweetId);

        List<Like> likes = likeRepository.findByTweetId(tweetId);

        return likes;
    }
    
    
}
