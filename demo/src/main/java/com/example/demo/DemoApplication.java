package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	//basicamente como decir si metes http://localhost:8080/holaMundo ejecuta codigo
	@GetMapping("/holaMundo")//mapeo que dice cuando te pidan holaMundo vas a ejecutar lo de abajo
	
	public String holaMundo() {
		return "hola mundo cruel";
	}
}
