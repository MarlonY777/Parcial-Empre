package com.Parcial3.app.Tables;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private int id;

    @NotNull
    private String titulo;

    @NotNull
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Usuario estudiante;

    @ManyToOne
    @JoinColumn(name = "id_director")
    private Usuario director;

    @ManyToOne
    @JoinColumn(name = "id_evaluador")
    private Usuario evaluador;

    @Enumerated(EnumType.STRING)
    private Estado calificacionDirector;

    @Enumerated(EnumType.STRING)
    private Estado calificacionEvaluador;

    private String archivo;

    private String descripcionCalificacionDirector;

    private String descripcionCalificacionEvaluador;

    public enum Estado {
        Pendiente, Aprobado, Rechazado
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Usuario estudiante) {
        this.estudiante = estudiante;
    }

    public Usuario getDirector() {
        return director;
    }

    public void setDirector(Usuario director) {
        this.director = director;
    }

    public Usuario getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(Usuario evaluador) {
        this.evaluador = evaluador;
    }

    public Estado getCalificacionDirector() {
        return calificacionDirector;
    }

    public void setCalificacionDirector(Estado calificacionDirector) {
        this.calificacionDirector = calificacionDirector;
    }

    public Estado getCalificacionEvaluador() {
        return calificacionEvaluador;
    }

    public void setCalificacionEvaluador(Estado calificacionEvaluador) {
        this.calificacionEvaluador = calificacionEvaluador;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getDescripcionCalificacionDirector() {
        return descripcionCalificacionDirector;
    }

    public void setDescripcionCalificacionDirector(String descripcionCalificacionDirector) {
        this.descripcionCalificacionDirector = descripcionCalificacionDirector;
    }

    public String getDescripcionCalificacionEvaluador() {
        return descripcionCalificacionEvaluador;
    }

    public void setDescripcionCalificacionEvaluador(String descripcionCalificacionEvaluador) {
        this.descripcionCalificacionEvaluador = descripcionCalificacionEvaluador;
    }
}
