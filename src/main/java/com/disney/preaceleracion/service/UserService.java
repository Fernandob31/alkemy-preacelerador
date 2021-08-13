package com.disney.preaceleracion.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.disney.preaceleracion.dto.Credencial;
import com.disney.preaceleracion.dto.UserDto;
import com.disney.preaceleracion.model.User;
import com.disney.preaceleracion.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MailService mailService;
	     
    
    public String iniciarSesion (Credencial credencial) throws NullPointerException{
    	User usuario = userRepository.findByUsername(credencial.getUsername());
    	if(usuario.getUserPass().equals(credencial.getUserpass())) {
    		String token = getJWTToken(credencial.getUsername());
    		return "Token de acceso: \n"+ token;
    	}else {
    		return "Datos Incorrectos";
    	}
    }
	
	public boolean registrarUsuario(UserDto user) throws IOException {
		if (!user.getUsername().isEmpty() && !user.getUserpass().isEmpty() && !user.getEmail().isEmpty()) {
			User usuario = user.buildEntity();
			userRepository.save(usuario);
			mailService.sendRegisterEmail(user);		//Envio de Email
			return true;
		}
		return false;
	}
	
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
}
