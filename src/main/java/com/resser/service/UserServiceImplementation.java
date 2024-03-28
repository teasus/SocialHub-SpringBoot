package com.resser.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resser.config.JwtProvider;
import com.resser.exception.UserException;
import com.resser.modal.User;
import com.resser.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException {

        Optional<User> optional = userRepository.findById(userId);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new UserException("User not found for id: " + userId);
        }
        return user;
    }

    @Override
    public User UserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("user not found with jwt user profile ");
        }

        return user;
    }

    @Override
    public User updateUser(Long userId, User req) throws UserException {
        User user = findUserById(userId);

        if (req.getFullName() != null) {
            user.setFullName(req.getFullName());
        }

        if (req.getImage() != null) {
            user.setImage(req.getImage());
        }
        if (req.getBackgroundImage() != null) {
            user.setBackgroundImage(req.getBackgroundImage());
        }

        if (req.getBirthDate() != null) {
            user.setBirthDate(req.getBirthDate());
        }

        if (req.getLocation() != null) {
            user.setLocation(req.getLocation());
        }

        if (req.getBio() != null) {
            user.setBio(req.getBio());
        }

        if (req.getWebsite() != null) {
            user.setWebsite(req.getWebsite());
        }

        return userRepository.save(user);

    }

    @Override
    public User followUser(Long userId, User followerUser) throws UserException {
        User gettingFollowedUser = findUserById(userId);
        if (gettingFollowedUser.getFollowers().contains(followerUser) && followerUser.getFollowings().contains(gettingFollowedUser)) {
            gettingFollowedUser.getFollowers().remove(followerUser);

            followerUser.getFollowings().remove(gettingFollowedUser);

        } else {
            gettingFollowedUser.getFollowers().add(followerUser);
            
            followerUser.getFollowings().add(gettingFollowedUser);
            
        }
        userRepository.save(followerUser);
        userRepository.save(gettingFollowedUser);

        return gettingFollowedUser;
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
       
        return userRepository.searchUser(query);
    }

}
