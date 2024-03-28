package com.resser.modal;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fullName ;
	private String location;
	private String birthDate ;
	private String email;
	private String password;
	private String mobile;
	private String image;
	private String backgroundImage;
	private String bio;
	private boolean req_user;
	private boolean isLogin_with_google;
	private String website;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Tweet> tweet = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Like> likes = new ArrayList<>();
	
	@Embedded
	private Verification verification ;
	
	@JsonIgnore
	@ManyToMany
	private List<User>followers = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	private List<User>followings = new ArrayList<>();

	
	
	
	
	
	
}
