package com.Parcial3.app.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Lógica para cerrar la sesión
        request.getSession().invalidate();
        return "redirect:/"; // Redirige al inicio de sesión
    }
}