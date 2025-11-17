package com.example.demo;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReponedorController {
    private final RepositorioStock repo = new RepositorioStock();
    @GetMapping("/reponedor")
    public String verReponedor(Model model) {
        model.addAttribute("stock", repo.getAll());
        return "reponedor";
    }
    @PostMapping("/reponedor/actualizar")
    public String actualizarStock(
            @RequestParam(name="productoExistente", required=false) String productoExistente,
            @RequestParam(name="productoNuevo", required=false) String productoNuevo,
            @RequestParam(name="cantidad") Integer cantidad,
            HttpSession session,
            Model model) {
        String producto;
        if (productoNuevo != null && !productoNuevo.trim().isEmpty()) {
            producto = productoNuevo.trim();
        } else if (productoExistente != null && !productoExistente.isEmpty()) {
            producto = productoExistente;
        } else {
            model.addAttribute("mensaje", "Debes indicar un producto.");
            model.addAttribute("stock", repo.getAll());
            return "reponedor";
        }
        Integer stockActual = repo.getOne(producto);
        if (stockActual == null) {
            stockActual = 0; 
        }
        Map<String, Integer> carrito = (Map<String, Integer>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new java.util.HashMap<>();
            session.setAttribute("carrito", carrito);
        }
        if (!carrito.containsKey(producto)) {
            carrito.put(producto, 0);
        }
        model.addAttribute("mensaje", "El stock de  se ha incrementado en " );
        model.addAttribute("stock", repo.getAll());
        return "reponedor";
    }
}
