package com.Parcial3.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.Parcial3.app.Tables.Proyecto;
import com.Parcial3.app.Repository.ProyectoRepository;

import java.util.List;

@Controller
public class EvaluadorController {

    @Autowired
    private ProyectoRepository proyectoRepository;

    // Mostrar la página principal del evaluador
    @GetMapping("/evaluador")
    public String evaluadorHome() {
        return "evaluador";
    }

    // Obtener proyectos calificados por el director
    @GetMapping("/evaluador/proyectosCalificados")
    @ResponseBody
    public List<Proyecto> getProyectosCalificados(@RequestParam int evaluadorId) {
        return proyectoRepository.findProyectosCalificadosPorDirector(evaluadorId);
    }

    // Calificar proyecto
    @PostMapping("/evaluador/calificarProyecto/{projectId}")
    @ResponseBody
    public ResponseEntity<?> calificarProyecto(@PathVariable int projectId, @RequestParam String calificacion) {
        Proyecto proyecto = proyectoRepository.findById(projectId).orElse(null);
        if (proyecto == null || proyecto.getEstado() != Proyecto.Estado.Aprobado) {
            return new ResponseEntity<>("Proyecto no disponible para evaluación", HttpStatus.BAD_REQUEST);
        }
        proyecto.setCalificacionEvaluador(Proyecto.Estado.valueOf(calificacion.toUpperCase()));
        proyectoRepository.save(proyecto);
        return new ResponseEntity<>("Proyecto calificado con éxito", HttpStatus.OK);
    }
}
