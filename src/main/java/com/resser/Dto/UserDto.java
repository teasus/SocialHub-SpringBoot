package com.resser.Dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String fullName;
    private String image;
    private String email;
    private String location;
    private String birthDate;
    private String website;
    private String backgroundImage;
    private String bio;
    private boolean req_user;
    private String mobile;

    private boolean isLogin_with_google;

    private List<UserDto> followers = new ArrayList<>();

    private List<UserDto> following = new ArrayList<>(); 

    private boolean followed;

    private boolean isVerfied;


}
