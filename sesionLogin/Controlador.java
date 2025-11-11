package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class Controlador {

    private static final String USUARIO_OK = "fer";
    private static final String PASSWORD_OK = "12345";

    // GET /
    @GetMapping("/")
    public String inicio(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        return cargarPagina("Inicio", session, model);
    }

    // GET /pagina1
    @GetMapping("/pagina1")
    public String pagina1(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        return cargarPagina("Página 1", session, model);
    }

    // GET /pagina2
    @GetMapping("/pagina2")
    public String pagina2(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        return cargarPagina("Página 2", session, model);
    }

    // GET /login
    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        if (session.getAttribute("usuario") != null) {
            return cargarPagina("Inicio", session, model);
        }
        return "login";
    }

    // POST /login
    @PostMapping("/login")
    public String formLogin(
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password,
            Model model,
            HttpSession session
    ) {
        if (USUARIO_OK.equals(usuario) && PASSWORD_OK.equals(password)) {
            session.setAttribute("usuario", usuario);
            return cargarPagina("Inicio", session, model);
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            model.addAttribute("usuarioPrevio", usuario);
            return "login";
        }
    }


    // Método auxiliar para reutilizar plantilla
    private String cargarPagina(String pagina, HttpSession session, Model model) {
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("pagina", pagina);
        return "plantilla"; // plantilla.html
    }

    // GET /logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}