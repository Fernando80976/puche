package com.example.demo;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CarritoController {

    private final RepositorioStock repo = new RepositorioStock();
    private final Map<String, Double> precios = new HashMap<>();

    @PostConstruct
    public void inicializarProductos() {
        precios.put("Figura Naruto", 29.99);
        precios.put("Figura Luffy", 34.99);
        precios.put("Figura Tanjiro", 32.50);
        precios.put("Manga One Piece Tomo 1", 8.99);
        precios.put("Manga Naruto Tomo 10", 7.50);
        precios.put("Manga Jujutsu Kaisen Tomo 3", 9.25);
        precios.put("Bluray Demon Slayer T1", 24.90);
        precios.put("Bluray Attack on Titan T3", 28.00);
        precios.put("Bluray Fullmetal Alchemist Brotherhood", 26.50);

        if (repo.getAll().isEmpty()) {
            repo.add("Figura Naruto", 5);
            repo.add("Figura Luffy", 3);
            repo.add("Figura Tanjiro", 4);
            repo.add("Manga One Piece Tomo 1", 12);
            repo.add("Manga Naruto Tomo 10", 10);
            repo.add("Manga Jujutsu Kaisen Tomo 3", 8);
            repo.add("Bluray Demon Slayer T1", 7);
            repo.add("Bluray Attack on Titan T3", 5);
            repo.add("Bluray Fullmetal Alchemist Brotherhood", 6);
        }
    }

    @GetMapping("/carrito")
    public String verCarrito(Model model, HttpSession session) {
        Map<String, Integer> carrito = obtenerCarrito(session);
        model.addAttribute("stock", repo.getAll());
        model.addAttribute("carrito", carrito);
        model.addAttribute("precios", precios);
        model.addAttribute("total", calcularTotal(carrito));
        return "carrito";
    }

    @PostMapping("/carrito/actualizar")
    public String actualizarCarrito(HttpSession session,
                                    @RequestParam Map<String, String> cantidades) {
        Map<String, Integer> carrito = obtenerCarrito(session);

        for (String producto : repo.getAll().keySet()) {
            if (cantidades.containsKey(producto)) {
                int pedido = Integer.parseInt(cantidades.get(producto));
                int stock = repo.getOne(producto);
                if (pedido > 0) {
                    carrito.put(producto, Math.min(pedido, stock));
                } else {
                    carrito.remove(producto);
                }
            }
        }

        session.setAttribute("carrito", carrito);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/finalizar")
    public String finalizarCompra(HttpSession session, Model model) {
        Map<String, Integer> carrito = obtenerCarrito(session);

        for (String producto : carrito.keySet()) {
            int pedido = carrito.get(producto);
            int stock = repo.getOne(producto);

            if (stock == 0) {
                model.addAttribute("error", "El producto '" + producto + "' no tiene stock disponible");
                model.addAttribute("stock", repo.getAll());
                model.addAttribute("carrito", carrito);
                model.addAttribute("precios", precios);
                model.addAttribute("total", calcularTotal(carrito));
                return "carrito";
            }

            if (pedido > stock) {
                model.addAttribute("error", "No hay stock suficiente para '" + producto + "'");
                model.addAttribute("stock", repo.getAll());
                model.addAttribute("carrito", carrito);
                model.addAttribute("precios", precios);
                model.addAttribute("total", calcularTotal(carrito));
                return "carrito";
            }
        }

        Map<String, Integer> compra = new HashMap<>(carrito);
        for (String producto : carrito.keySet()) {
            int stock = repo.getOne(producto);
            repo.modify(producto, stock - carrito.get(producto));
        }

        session.setAttribute("carrito", new HashMap<>());
        model.addAttribute("compra", compra);
        model.addAttribute("precios", precios);
        model.addAttribute("total", calcularTotal(compra));

        return "compra_finalizada";
    }


    private Map<String, Integer> obtenerCarrito(HttpSession session) {
        Map<String, Integer> carrito = (Map<String, Integer>) session.getAttribute("carrito");
        if (carrito == null || carrito.isEmpty()) {
            carrito = new HashMap<>();
            session.setAttribute("carrito", carrito);
        }
        return carrito;
    }

    private double calcularTotal(Map<String, Integer> carrito) {
        double total = 0.0;
        for (String producto : carrito.keySet()) {
            total += carrito.get(producto) * precios.getOrDefault(producto, 10.0);
        }
        return total;
    }
}
