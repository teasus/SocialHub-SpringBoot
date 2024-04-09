package com.resser.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.resser.exception.UserException;

import com.resser.modal.Tweet;
import com.resser.modal.User;
import com.resser.repository.TweetRepository;
import com.resser.request.TweetReplyRequest;


@Service
public class TweetServiceImplementation implements TweetService {
    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public Tweet createTweet(Tweet req, User user) throws UserException {

        Tweet tweet = new Tweet();
        tweet.setContent(req.getContent());
        tweet.setCreatedAt(LocalDateTime.now());
        tweet.setUser(user);
        tweet.setImage(req.getImage());
        tweet.setVideo(req.getVideo());
        tweet.setReply(false);
        tweet.setTweet(true);
       

        return  tweetRepository.save(tweet);
        
        
    }

    @Override
    public List<Tweet> findAllTweets() throws UserException {
        
        //public List<Tweet> findAllTweet;

        return tweetRepository.findAllByIsTweetTrueOrderByCreatedAtDesc();

       
    }

    @Override
    public Tweet reTweet(Long tweetId, User user) throws UserException, TweetException {
        
        

        Tweet tweet = findById(tweetId);

        if(tweet.getReTweetUser().contains(user)) {
            tweet.getReTweetUser().remove(user);

        }else {
            tweet.getReTweetUser().add(user);
        }
        

        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet findById(Long tweetId) throws TweetException {
        
        Tweet tweet = tweetRepository.getReferenceById(tweetId);

        if(tweet == null) {
            throw new TweetException("tweet not found with id " + tweetId);
        }
        return tweet;
    }

    @Override
    public void deleteTweetById(Long tweetId, Long userId) throws TweetException, UserException {
            Tweet tweet = findById(tweetId);

            if(!userId.equals(tweet.getUser().getId())){
                throw new UserException("You cant delte another user Tweet " + tweetId);
            }

            tweetRepository.deleteById(tweet.getId());
    }

    @Override
    public Tweet removeFromRetweet(Long tweetId, User user) throws TweetException, UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromRetweet'");
    }

    @Override
    public Tweet createReply(TweetReplyRequest req, User user) throws TweetException {

        Tweet replyFor = findById(req.getTweetId());

        Tweet tweet = new Tweet();
        tweet.setContent(req.getContent());
        tweet.setCreatedAt(LocalDateTime.now());
        tweet.setUser(user);
        tweet.setImage(req.getImage());
        tweet.setReply(true);
        tweet.setTweet(false);
        tweet.setReplyFor(replyFor);

       Tweet savedReplyTweet = tweetRepository.save(tweet);

       replyFor.getReplyTweet().add(savedReplyTweet);
       tweetRepository.save(replyFor);

        return replyFor ;
    }

    @Override
    public List<Tweet> getUserTweet(User user) throws UserException {
       return tweetRepository.findAllByReTweetUserContainsOrUserIdAndIsTweetTrueOrderByCreatedAtDesc(user,user.getId());
    }

    @Override
    public List<Tweet> getByLikesContainsUser(User user) throws UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByLikesContainsUser'");
    }

}
