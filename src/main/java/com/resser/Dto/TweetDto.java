package com.resser.Dto;

import java.time.LocalDateTime;
import java.util.List;

import com.resser.modal.Tweet;

import lombok.Data;

@Data
public class TweetDto {

        private Long id;

        private UserDto user;
        
        private String content;

        private String image;

        private String video;

        private boolean isReply;

        private boolean isTweet;

        private int totalLikes;

        private int totalReplies;

        private int totalReTweets;

        //did the user liked it or not
        private boolean isLiked;
        //did the user retweeted it or not
        private boolean isReTweet;

        private LocalDateTime createdAt;

        private List<TweetDto> replyTweets;
        private List<Long> reTweetUserId;

 
    
}
