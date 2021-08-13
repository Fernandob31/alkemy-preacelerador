package com.disney.preaceleracion.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.disney.preaceleracion.dto.UserDto;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class MailService {
	
	@Value("${preaceleracion.sendgrid.apikey}")
	private String apiKey;

	@Value("${preaceleracion.sendgrid.email}")
	private String email;	
	
	public void sendRegisterEmail(UserDto user) throws IOException {
		Email from = new Email(email);
		String subject = "Registracion Existosa!";
		Email to = new Email(user.getEmail());
		
		String textoEmail = "Registro exitoso\n" + "Bienvenido " + user.getUsername() + 
				"\n Te registraste con el siguiente email " + user.getEmail() +
				"\n ";
		Content content = new Content("text/plain", textoEmail);
		Mail mail = new Mail(from, subject, to, content);
		
		SendGrid sg = new SendGrid(apiKey);
		Request request = new Request();
		try
		{
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		}catch(
		IOException ex)
		{
			throw ex;
		}
	
	}
}
