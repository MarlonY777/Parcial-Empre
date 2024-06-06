package com.Parcial3.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.Parcial3.app.Tables.Proyecto;
import com.Parcial3.app.Tables.Usuario;
import com.Parcial3.app.Repository.ProyectoRepository;

import java.util.List;

@Controller
public class EstudianteController {

    @Autowired
    private ProyectoRepository proyectoRepository;

    // Mostrar la página principal del estudiante
    @GetMapping("/estudiante")
    public String estudianteHome() {
        return "estudiante";
    }
    
    @GetMapping("/estudiante/seleccionarIdeaProyecto")
    public String seleccionarIdeaProyecto() {
        return "seleccionarIdeaProyecto";
    }

    // Obtener proyectos disponibles para asignación
    @GetMapping("/estudiante/proyectosDisponibles")
    @ResponseBody
    public List<Proyecto> getProyectosDisponibles() {
        return proyectoRepository.findProyectosSinEstudianteAsignado();
    }

    // Obtener el proyecto asignado al estudiante
    @GetMapping("/estudiante/miProyecto")
    @ResponseBody
    public Proyecto getMiProyecto(@RequestParam int estudianteId) {
        return proyectoRepository.findProyectoByEstudianteId(estudianteId);
    }

    // Asignar un proyecto al estudiante
    @PostMapping("/estudiante/asignarProyecto/{projectId}")
    @ResponseBody
    public ResponseEntity<?> asignarProyecto(@PathVariable int projectId, @RequestParam int estudianteId) {
        Proyecto proyecto = proyectoRepository.findById(projectId).orElse(null);
        if (proyecto == null || proyecto.getEstudiante() != null) {
            return new ResponseEntity<>("Proyecto no disponible", HttpStatus.BAD_REQUEST);
        }
        Usuario estudiante = new Usuario();
        estudiante.setId(estudianteId); // Establecer el ID del estudiante
        proyecto.setEstudiante(estudiante); // Asignar el estudiante al proyecto
        proyectoRepository.save(proyecto);
        return new ResponseEntity<>("Proyecto asignado con éxito", HttpStatus.OK);
    }
}
