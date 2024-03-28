package com.resser.Util;

import com.resser.modal.Like;
import com.resser.modal.Tweet;
import com.resser.modal.User;

public class TweetUtil {

    public final static boolean isLikedByReqUser(User reqUser,Tweet tweet){

        for(Like like: tweet.getLikes()){
            if(like.getUser().getId().equals(reqUser.getId())){
                return true;
            }

        }
        return false;

    }

    public final static boolean isReTweetedByReqUser(User reqUser,Tweet tweet){

        for(User user: tweet.getReTweetUser()){
            if(user.getId().equals(reqUser.getId())){
                return true;
            }

        }
        return false;

    }
    
}
