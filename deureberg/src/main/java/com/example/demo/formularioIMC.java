package com.example.demo;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PostMapping;
	
	
	@Controller


public class formularioIMC {


	  @GetMapping("/formularioIMC")
	  public String greetingForm(Model model) {
	    model.addAttribute("formularioIMC", new formularioIMC());
	    return "formularioIMC";
	  }

	  @PostMapping("/formularioIMC")
	  public String greetingSubmit(@ModelAttribute formularioIMC formularioIMC, Model model) {
	    model.addAttribute("formularioIMC", formularioIMC);
	    return "result";
	  }

	
}
