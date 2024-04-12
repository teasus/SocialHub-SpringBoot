package com.resser.Response;

import com.resser.modal.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse extends User {
	
	private String jwt;
	private boolean status;
	

}
