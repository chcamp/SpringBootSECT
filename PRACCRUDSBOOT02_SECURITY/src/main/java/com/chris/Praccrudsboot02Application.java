package com.chris;

import org.springframework.boot.SpringApplication;	
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Praccrudsboot02Application {

	public static void main(String[] args) {
		
		//para configurar el puerto diferente del tomcat
				System.getProperties().put("server.port", 8181); //8181 port seteado
				
				
		SpringApplication.run(Praccrudsboot02Application.class, args);
	}
}
