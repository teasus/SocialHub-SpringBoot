package com.resser.Dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.resser.Dto.LikeDto;
import com.resser.Dto.TweetDto;
import com.resser.Dto.UserDto;
import com.resser.modal.Like;
import com.resser.modal.User;

public class LikeDtoMapper {

    public static LikeDto toLikeDtos(Like like, User reqUser) {

        UserDto user = UserDtoMapper.toUserDto(like.getUser());
        UserDto reqUserDto = UserDtoMapper.toUserDto(reqUser);

        TweetDto tweet = TweetDtoMapper.toTweetDto(like.getTweet(), reqUser);

        LikeDto likeDto = new LikeDto();

        likeDto.setId(like.getId());
        likeDto.setTweet(tweet);
        likeDto.setUser(user);

        return likeDto;
    }

    public static List<LikeDto> toLikeDtos(List<Like> likes, User reqUser) {

        List<LikeDto> likeDtos = new ArrayList<>();

        for (Like like : likes) {
            UserDto user = UserDtoMapper.toUserDto(like.getUser());
            TweetDto tweet = TweetDtoMapper.toTweetDto(like.getTweet(), reqUser);
            // LikeDto toLikeDto = LikeDtoMapper.toLikeDtos(like, reqUser);
            LikeDto likeDto = new LikeDto();
            likeDto.setId(like.getId());
            likeDto.setTweet(tweet);
            likeDto.setUser(user);
            likeDtos.add(likeDto);

        }

        return likeDtos;
    }

}
