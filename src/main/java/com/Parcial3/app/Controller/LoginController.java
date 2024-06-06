package com.Parcial3.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Parcial3.app.Repository.UsuarioRepository;
import com.Parcial3.app.Tables.Usuario;

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("correo") String correo, @RequestParam("password") String password, Model model) {
        Usuario usuario = usuarioRepository.findByCorreoAndPassword(correo, password);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            switch (usuario.getTipoUsuario()) {
                case Administrador:
                    return "redirect:/admin"; // Redirigir a la URL de la página del administrador
                case Coordinador:
                    return "redirect:/coordinador"; // Redirigir a la URL de la página del coordinador
                case Director:
                    return "redirect:/director"; // Redirigir a la URL de la página del director
                case Evaluador:
                    return "redirect:/evaluador"; // Redirigir a la URL de la página del evaluador
                case Estudiante:
                    return "redirect:/estudiante"; // Redirigir a la URL de la página del estudiante
                default:
                    return "redirect:/login";
            }
        } else {
            return "redirect:/login?error=true";
        }
    }
}
