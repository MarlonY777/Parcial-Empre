package com.Parcial3.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Parcial3.app.Tables.Usuario;
import com.Parcial3.app.Tables.Usuario.TipoUsuario;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Buscar usuarios por tipo de usuario
    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);

    // Buscar usuario por correo y contraseña
    Usuario findByCorreoAndPassword(String correo, String password);
    
    
}
