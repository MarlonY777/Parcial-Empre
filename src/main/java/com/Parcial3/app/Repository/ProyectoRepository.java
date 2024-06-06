package com.Parcial3.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.Parcial3.app.Tables.Proyecto;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    // Buscar proyectos que no tienen estudiante asignado
    @Query("SELECT p FROM Proyecto p WHERE p.estudiante IS NULL")
    List<Proyecto> findProyectosSinEstudianteAsignado();

    // Buscar proyectos asignados a un director específico
    @Query("SELECT p FROM Proyecto p WHERE p.director.id = :directorId")
    List<Proyecto> findProyectosByDirectorId(int directorId);

    // Buscar el proyecto asignado a un estudiante específico
    @Query("SELECT p FROM Proyecto p WHERE p.estudiante.id = :estudianteId")
    Proyecto findProyectoByEstudianteId(int estudianteId);

    // Buscar proyectos calificados por el director
    @Query("SELECT p FROM Proyecto p WHERE p.director.id = :evaluadorId AND p.estado = 'CALIFICADO'")
    List<Proyecto> findProyectosCalificadosPorDirector(int evaluadorId);

    // Buscar proyectos por cédula del estudiante
    @Query("SELECT p FROM Proyecto p WHERE p.estudiante.cedula = :cedula")
    List<Proyecto> findByEstudianteCedula(String cedula);
}
