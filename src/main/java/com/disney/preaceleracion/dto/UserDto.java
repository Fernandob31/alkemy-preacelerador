package com.disney.preaceleracion.dto;

import com.disney.preaceleracion.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String username;
	
	private String userpass;
	
	private String email;
	
	public User buildEntity() {
		return User.builder()
				.username(this.username)
				.userPass(this.userpass)
				.userEmail(this.email)
				.build();
	}
	
}
