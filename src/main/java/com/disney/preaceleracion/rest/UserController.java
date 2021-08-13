package com.disney.preaceleracion.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disney.preaceleracion.dto.Credencial;
import com.disney.preaceleracion.dto.UserDto;
import com.disney.preaceleracion.service.UserService;

@RestController
@RequestMapping("auth")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public String login(@RequestBody Credencial credencial) {
		try {
			return userService.iniciarSesion(credencial);
		}catch(NullPointerException ex){
			return "Datos Invalidos";
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> signIn(@RequestBody UserDto usuario) {
		try {
			if (userService.registrarUsuario(usuario)) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("Datos registrados con exito");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Usuario no registrado. Faltan Datos");
			}
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error Interno");
		}
	}
}