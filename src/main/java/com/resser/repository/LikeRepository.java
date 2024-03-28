package com.resser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.resser.modal.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long>{

//find wheather a user litked a tweet or not
    @Query("SELECT l FROM Like l WHERE l.user.id=:userId AND l.tweet.id=:tweetId")
    public Like isLikeExist(@Param("userId") Long userId,@Param("tweetId") Long tweetId);
    //find no of lieks from tweet
    @Query("SELECT l FROM Like l WHERE l.tweet.id=:tweetId")
    public List<Like> findByTweetId(@Param("tweetId") Long tweetId);
    
} 