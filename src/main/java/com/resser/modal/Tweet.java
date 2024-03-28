package com.resser.modal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Tweet {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long id;
	
	@ManyToOne
	private User user;
	
	private String content;
	private String image;
	private String video;
	
	private boolean isReply;
	private boolean isTweet;
	@JsonIgnore
	@OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
	private List<Like> likes = new ArrayList<>();
	
	@OneToMany
	private List<Tweet> replyTweet = new ArrayList<>();
	
	@ManyToMany
	private List<User> reTweetUser = new ArrayList<>();
	
	@ManyToOne
	private Tweet replyFor;

	private LocalDateTime createdAt ;

	
	
	
	

	

}
