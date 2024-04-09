package com.resser.Dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.resser.Dto.TweetDto;
import com.resser.Dto.UserDto;
import com.resser.Util.TweetUtil;
import com.resser.modal.Tweet;
import com.resser.modal.User;

public class TweetDtoMapper {

    public static TweetDto toTweetDto(Tweet tweet, User reqUser) {

        UserDto user = UserDtoMapper.toUserDto(reqUser);
        boolean isLiked = TweetUtil.isLikedByReqUser(reqUser, tweet);
        boolean isRetweeted=TweetUtil.isReTweetedByReqUser(reqUser, tweet);

        List<Long> reTweetUserId = new ArrayList<>();

        for (User reTweetedUsers : tweet.getReTweetUser()) {
            reTweetUserId.add(reTweetedUsers.getId());
        }

        TweetDto tweetDto = new TweetDto();
        tweetDto.setId(tweet.getId());
        tweetDto.setContent(tweet.getContent());
        tweetDto.setCreatedAt(tweet.getCreatedAt());
        tweetDto.setImage(tweet.getImage());
        tweetDto.setTotalLikes(tweet.getLikes().size());
        tweetDto.setTotalReplies(tweet.getReplyTweet().size());
        tweetDto.setTotalReTweets(tweet.getReTweetUser().size());
        tweetDto.setUser(user);
        tweetDto.setLiked(isLiked);
        tweetDto.setVideo(tweet.getVideo());
        tweetDto.setReply(tweet.isReply());
        tweetDto.setReTweet(isRetweeted);
        tweetDto.setReTweetUserId(reTweetUserId);
        tweetDto.setReplyTweets(toTweetDtos(tweet.getReplyTweet(),reqUser));


        return tweetDto;
    }

    public static List<TweetDto> toTweetDtos(List<Tweet> tweets, User reqUser) {
        List<TweetDto> tweetDtos = new ArrayList<>();
        for(Tweet tweet: tweets){
            TweetDto tweetDto = toReplyTweetDto(tweet,reqUser);
            tweetDtos.add(tweetDto);
        }
        return tweetDtos;
    }

    private static TweetDto toReplyTweetDto(Tweet tweet, User reqUser) {
        // TODO Auto-generated method stub
        UserDto user = UserDtoMapper.toUserDto(reqUser);
        boolean isLiked = TweetUtil.isLikedByReqUser(reqUser, tweet);
        boolean isRetweeted=TweetUtil.isReTweetedByReqUser(reqUser, tweet);

        List<Long> reTweetUserId = new ArrayList<>();

        for (User reTweetedUsers : tweet.getReTweetUser()) {
            reTweetUserId.add(reTweetedUsers.getId());
        }

        TweetDto tweetDto = new TweetDto();
        tweetDto.setId(tweet.getId());
        tweetDto.setContent(tweet.getContent());
        tweetDto.setCreatedAt(tweet.getCreatedAt());
        tweetDto.setImage(tweet.getImage());
        tweetDto.setTotalLikes(tweet.getLikes().size());
        tweetDto.setTotalReplies(tweet.getReplyTweet().size());
        tweetDto.setTotalReTweets(tweet.getReTweetUser().size());
        tweetDto.setUser(user);
        tweetDto.setLiked(isLiked);
        tweetDto.setVideo(tweet.getVideo());
        tweetDto.setReply(tweet.isReply());
        tweetDto.setReplyTweets(toTweetDtos(tweet.getReplyTweet(), reqUser));
        tweetDto.setReTweet(isRetweeted);
        tweetDto.setReTweetUserId(reTweetUserId);
        

        return tweetDto;
    }

}
