package com.resser.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resser.Dto.UserDto;
import com.resser.Dto.mapper.UserDtoMapper;
import com.resser.Util.UserUtil;
import com.resser.exception.UserException;
import com.resser.modal.User;
import com.resser.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt)
            throws UserException {
        User user = userService.UserProfileByJwt(jwt);

        UserDto userDto = UserDtoMapper.toUserDto(user);

        userDto.setReq_user(true);

        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@RequestHeader("Authorization") String jwt,
            @PathVariable("userId") Long userId)
            throws UserException {
        User reqUser = userService.UserProfileByJwt(jwt);

        User userById = userService.findUserById(userId);

        UserDto userDto = UserDtoMapper.toUserDto(userById);

        userDto.setReq_user(UserUtil.isReqUser(reqUser, userById));
        userDto.setFollowed(UserUtil.isFollowedByReqUser(reqUser, userById));

        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/searchUser")
    public ResponseEntity<List<UserDto>> searchUser(@RequestHeader("Authorization") String jwt,
            @RequestParam("Query") String Query)
            throws UserException {
        User user = userService.UserProfileByJwt(jwt);

        List<User> results = userService.searchUser(Query);

        List<UserDto> userDto = UserDtoMapper.toUserDto(results);

        

        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }


    @PutMapping("/update")
    public ResponseEntity<UserDto> updateProfile(@RequestBody User req, @RequestHeader("Authorization") String jwt)
            throws UserException {
        User user = userService.UserProfileByJwt(jwt);

        User updateUser = userService.updateUser(user.getId(), req);

        UserDto userDto = UserDtoMapper.toUserDto(updateUser);

        
        

        return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
    }

    @PutMapping("/{userId}/follow")
    public ResponseEntity<UserDto> followUser(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String jwt)
            throws UserException {
        User reqUser = userService.UserProfileByJwt(jwt);

        User user = userService.followUser(userId, reqUser);

        UserDto userDto = UserDtoMapper.toUserDto(user);

        userDto.setFollowed(UserUtil.isFollowedByReqUser(reqUser, user));
  
        return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
    }

}
