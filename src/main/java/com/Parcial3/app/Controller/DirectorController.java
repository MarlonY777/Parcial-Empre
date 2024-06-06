package com.Parcial3.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.Parcial3.app.Tables.Proyecto;
import com.Parcial3.app.Tables.Usuario;
import com.Parcial3.app.Repository.ProyectoRepository;
import com.Parcial3.app.Repository.UsuarioRepository;

import java.util.List;

@Controller
public class DirectorController {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Mostrar la página principal del director
    @GetMapping("/director")
    public String directorHome() {
        return "director";
    }

    // Obtener la lista de estudiantes
    @GetMapping("/director/estudiantes")
    @ResponseBody
    public List<Usuario> getEstudiantes() {
        return usuarioRepository.findByTipoUsuario(Usuario.TipoUsuario.Estudiante);
    }

    // Obtener proyectos asignados al director
    @GetMapping("/director/proyectosAsignados")
    @ResponseBody
    public List<Proyecto> getProyectosAsignados(@RequestParam int directorId) {
        return proyectoRepository.findProyectosByDirectorId(directorId);
    }

    // Calificar proyecto
    @PostMapping("/director/calificarProyecto/{projectId}")
    @ResponseBody
    public ResponseEntity<?> calificarProyecto(@PathVariable int projectId, @RequestParam String estado) {
        Proyecto proyecto = proyectoRepository.findById(projectId).orElse(null);
        if (proyecto == null) {
            return new ResponseEntity<>("Proyecto no encontrado", HttpStatus.NOT_FOUND);
        }
        proyecto.setEstado(Proyecto.Estado.valueOf(estado.toUpperCase()));
        proyectoRepository.save(proyecto);
        return new ResponseEntity<>("Proyecto calificado con éxito", HttpStatus.OK);
    }
}
