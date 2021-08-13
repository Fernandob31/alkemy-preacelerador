package com.disney.preaceleracion.dto;


import org.springframework.context.annotation.ComponentScan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ComponentScan
public class Credencial {
	private String username;
	private String userpass;
}
