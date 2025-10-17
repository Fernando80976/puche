package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mapeosHolaMundo {
	
 @RequestMapping("/hola")
 
 @ResponseBody
 //lo que responda este cuerpo sera respuesta http
public String holaMundo(@RequestParam(name="nombre",required=false,defaultValue="mundo")String nombre){//para obtener el nombre y que puede ser false
	 
//	 if(nombre==null || nombre.isBlank()) {
//		 return "hola mundo";
//
//	 }
//	 else
		 return "hola "+nombre;
 }
 //basicamente como decir si metes http://localhost:8080/holaMundo ejecuta codigo@GetMapping("/holaMundo")//mapeo que dice cuando te pidan holaMundo vas a ejecutar lo de abajopublic String holaMundo() 
 
// @GetMapping("/hola2")
// public String holaMundo2(){
//	 return "index.html";
// }
 
}
