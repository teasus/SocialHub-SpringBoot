package com.resser.Util;

import com.resser.modal.User;

public class UserUtil {

    public static final boolean isReqUser(User reqUser,User mainUser){
        return reqUser.getId().equals(mainUser.getId());
    }

    public static final boolean isFollowedByReqUser(User reqUser,User user2){
        return reqUser.getFollowings().contains(user2);
    }
    
}
