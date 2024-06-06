package com.Parcial3.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.Parcial3.app.Tables.Usuario;
import com.Parcial3.app.Repository.UsuarioRepository;

import java.util.List;
@Controller
public class AdminController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/admin")
    public String adminHome() {
        return "administrador";
    }
    
    // Mostrar la página de administración de usuarios
    @GetMapping("/admin/administrarUsuarios")
    public String administrarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "administrarUsuarios";
    }

    // Obtener todos los usuarios (API REST)
    @GetMapping("/admin/usuarios")
    @ResponseBody
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario específico por id (API REST)
    @GetMapping("/admin/usuarios/{id}")
    @ResponseBody
    public Usuario getUsuarioById(@PathVariable int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Crear un nuevo usuario (API REST)
    @PostMapping("/admin/usuarios")
    @ResponseBody
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Actualizar un usuario existente (API REST)
    @PutMapping("/admin/usuarios/{id}")
    @ResponseBody
    public Usuario updateUsuario(@PathVariable int id, @RequestBody Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setCedula(usuarioDetails.getCedula());
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setCorreo(usuarioDetails.getCorreo());
            usuario.setPassword(usuarioDetails.getPassword());
            usuario.setTipoUsuario(usuarioDetails.getTipoUsuario());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    // Eliminar un usuario (API REST)
    @DeleteMapping("/admin/usuarios/{id}")
    @ResponseBody
    public void deleteUsuario(@PathVariable int id) {
        usuarioRepository.deleteById(id);
    }
}