package com.resser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.resser.exception.UserException;
import com.resser.modal.User;

@Service
public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User UserProfileByJwt(String jwt) throws UserException;

    public User updateUser(Long userId,User user) throws UserException;
    
    public User followUser(Long userId,User user) throws UserException;

    public List<User> searchUser(String query) throws UserException;
    public List<User> getAllUserList() throws UserException;
}
