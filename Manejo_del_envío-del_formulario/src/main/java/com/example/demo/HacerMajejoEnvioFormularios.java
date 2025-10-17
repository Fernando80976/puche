package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class HacerMajejoEnvioFormularios {
	

	  @GetMapping("/greeting")
	  public String greetingForm(Model modelo) {
	    modelo.addAttribute("greeting", new Greeting());
	    return "greeting";
	  }

	  @PostMapping("/greeting")
	  public String greetingSubmit(@ModelAttribute Greeting greeting, Model modelo) {
	    modelo.addAttribute("greeting", greeting);
	    return "result";
	  }

	
}
