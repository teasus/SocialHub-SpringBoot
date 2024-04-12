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

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBackgroundImage() {
		return this.backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public boolean isReq_user() {
		return this.req_user;
	}

	public boolean getReq_user() {
		return this.req_user;
	}

	public void setReq_user(boolean req_user) {
		this.req_user = req_user;
	}

	public boolean isIsLogin_with_google() {
		return this.isLogin_with_google;
	}

	public boolean getIsLogin_with_google() {
		return this.isLogin_with_google;
	}

	public void setIsLogin_with_google(boolean isLogin_with_google) {
		this.isLogin_with_google = isLogin_with_google;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<Tweet> getTweet() {
		return this.tweet;
	}

	public void setTweet(List<Tweet> tweet) {
		this.tweet = tweet;
	}

	public List<Like> getLikes() {
		return this.likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public Verification getVerification() {
		return this.verification;
	}

	public void setVerification(Verification verification) {
		this.verification = verification;
	}

	public List<User> getFollowers() {
		return this.followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<User> getFollowings() {
		return this.followings;
	}

	public void setFollowings(List<User> followings) {
		this.followings = followings;
	}
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
