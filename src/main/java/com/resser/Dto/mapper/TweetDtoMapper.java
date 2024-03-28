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

        List<Long> reTweetUserId = new ArrayList<>();

        for (User reTweetedUsers : tweet.getReTweetUser()) {
            reTweetUserId.add(reTweetedUsers.getId());
        }

        return null;
    }

}
