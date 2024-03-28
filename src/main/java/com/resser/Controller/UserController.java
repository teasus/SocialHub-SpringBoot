package com.resser.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resser.Dto.UserDto;
import com.resser.Dto.mapper.UserDtoMapper;
import com.resser.exception.UserException;
import com.resser.modal.User;
import com.resser.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt) 
    throws UserException
    {
        User user = userService.UserProfileByJwt(jwt);

        UserDto userDto = UserDtoMapper.toUserDto(user);


        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }

}
