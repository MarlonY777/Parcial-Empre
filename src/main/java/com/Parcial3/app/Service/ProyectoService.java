package com.Parcial3.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Parcial3.app.Repository.ProyectoRepository;
import com.Parcial3.app.Tables.Proyecto;
import com.Parcial3.app.Tables.Usuario;

import java.util.List;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Proyecto> getProyectosDisponibles() {
        return proyectoRepository.findProyectosSinEstudianteAsignado();
    }

    public Proyecto getMiProyecto(int estudianteId) {
        return proyectoRepository.findProyectoByEstudianteId(estudianteId);
    }

    public void asignarProyecto(int projectId, Usuario estudiante) {
        Proyecto proyecto = proyectoRepository.findById(projectId).orElse(null);
        if (proyecto != null && proyecto.getEstudiante() == null) {
            proyecto.setEstudiante(estudiante);
            proyectoRepository.save(proyecto);
        }
    }

    public List<Proyecto> getProyectosAsignados(int directorId) {
        return proyectoRepository.findProyectosByDirectorId(directorId);
    }

    public void calificarProyecto(int projectId, String estado) {
        Proyecto proyecto = proyectoRepository.findById(projectId).orElse(null);
        if (proyecto != null) {
            proyecto.setEstado(Proyecto.Estado.valueOf(estado.toUpperCase()));
            proyectoRepository.save(proyecto);
        }
    }

    public List<Proyecto> getProyectosCalificados(int evaluadorId) {
        return proyectoRepository.findProyectosCalificadosPorDirector(evaluadorId);
    }

    public void calificarProyectoEvaluador(int projectId, String calificacion) {
        Proyecto proyecto = proyectoRepository.findById(projectId).orElse(null);
        if (proyecto != null && proyecto.getEstado() == Proyecto.Estado.Aprobado) {
            proyecto.setCalificacionEvaluador(Proyecto.Estado.valueOf(calificacion.toUpperCase()));
            proyectoRepository.save(proyecto);
        }
    }

    public List<Proyecto> findByEstudianteCedula(String cedula) {
        return proyectoRepository.findByEstudianteCedula(cedula);
    }
}
