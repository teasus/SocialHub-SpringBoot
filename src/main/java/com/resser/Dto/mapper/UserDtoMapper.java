package com.resser.Dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.resser.Dto.UserDto;
import com.resser.modal.User;

public class UserDtoMapper {
    
    public static UserDto toUserDto(User user){
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFullName(user.getFullName());
            userDto.setEmail(user.getEmail());
            userDto.setImage(user.getImage());
            userDto.setBackgroundImage(user.getBackgroundImage());
            userDto.setBio(user.getBio());
            userDto.setBirthDate(user.getBirthDate());
            userDto.setLocation(user.getLocation());
            userDto.setFollowers(toUserDto(user.getFollowers()));
            userDto.setFollowing(toUserDto(user.getFollowings()));
            userDto.setLogin_with_google(user.isLogin_with_google());
            // userDto.setVerfied(false);
            return userDto;
    }

    private static List<UserDto> toUserDto(List<User> followers) {
        List<UserDto> userDtos = new ArrayList<>();
        
        for(User user: followers  ){
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFullName(user.getFullName());
            userDto.setEmail(user.getEmail());
            userDto.setImage(user.getImage());
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
