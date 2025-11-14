package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ControllerCompra {
	static RepositorioStock stock=new RepositorioStock(); 
	public static void añadirStock() {
		stock.add("Figura de anime persona5", 666);
		stock.add("dakimakura de anime ", 5000);
		stock.add("Mangas super edicion limitada de tu anime favorito con ilustraciones especiales por la celebracion del 10º aniversario de nuestra amada obra audiovisual", 10);
		stock.save();
	}
@GetMapping("/")
public String inicio(Model modelo) {
 
    return "inicio"; 
}


}
