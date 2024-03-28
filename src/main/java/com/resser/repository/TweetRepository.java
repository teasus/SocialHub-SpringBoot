package com.resser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.resser.modal.Tweet;
import com.resser.modal.User;


@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long>{


    
    public List<Tweet> findAllByIsTweetTrueOrderByCreatedAtDesc(); 

    public List<Tweet> findAllByReTweetUserContainsOrUserIdAndIsTweetTrueOrderByCreatedAtDesc(User user,Long userId);
    //returns all tweets like by user id
    public List<Tweet> findByLikesContainingOrderByCreatedAtDesc(User user);

    @Query("Select t From Tweet t JOIN t.likes l where l.user.id=:userId")
    public List<Tweet> findByLikesUserId(Long userId);
    
}
