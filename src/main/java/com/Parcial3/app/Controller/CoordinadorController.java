package com.Parcial3.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.Parcial3.app.Tables.Proyecto;
import com.Parcial3.app.Tables.Usuario;
import com.Parcial3.app.Tables.Usuario.TipoUsuario;
import com.Parcial3.app.Repository.ProyectoRepository;
import com.Parcial3.app.Repository.UsuarioRepository;
import com.Parcial3.app.Service.ProyectoService;

import java.util.List;

@Controller
public class CoordinadorController {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProyectoService proyectoService;

    // Mostrar la página principal del coordinador
    @GetMapping("/coordinador")
    public String coordiHome() {
        return "coordinador";
    }

    // Obtener todos los usuarios de un tipo específico (API REST)
    @GetMapping("/coordinador/usuarios/tipo/{tipo}")
    @ResponseBody
    public List<Usuario> getUsuariosByTipo(@PathVariable String tipo) {
        // Convertir el String al tipo de enumeración TipoUsuario
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipo);
        // Llamar al método del repositorio para buscar usuarios por tipo de usuario
        return usuarioRepository.findByTipoUsuario(tipoUsuario);
    }

    @GetMapping("/coordinador/directores")
    @ResponseBody
    public List<Usuario> getAllDirectores() {
        return usuarioRepository.findByTipoUsuario(TipoUsuario.Director);
    }

    @GetMapping("/coordinador/evaluadores")
    @ResponseBody
    public List<Usuario> getAllEvaluadores() {
        return usuarioRepository.findByTipoUsuario(TipoUsuario.Evaluador);
    }

    // Mostrar la página de administración de proyectos
    @GetMapping("/coordinador/administrarProyectos")
    public String administrarProyectos(Model model) {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        model.addAttribute("proyectos", proyectos);
        return "administrarProyectos";
    }

    // Obtener todos los proyectos (API REST)
    @GetMapping("/coordinador/proyectos")
    @ResponseBody
    public List<Proyecto> getAllProyectos() {
        return proyectoRepository.findAll();
    }

    // Obtener un proyecto específico por id (API REST)
    @GetMapping("/coordinador/proyectos/{id}")
    @ResponseBody
    public Proyecto getProyectoById(@PathVariable int id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    // Crear un nuevo proyecto (API REST)
    @PostMapping("/coordinador/proyectos")
    @ResponseBody
    public Proyecto createProyecto(@RequestBody Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    // Actualizar un proyecto existente (API REST)
    @PutMapping("/coordinador/proyectos/{id}")
    @ResponseBody
    public Proyecto updateProyecto(@PathVariable int id, @RequestBody Proyecto proyectoDetails) {
        Proyecto proyecto = proyectoRepository.findById(id).orElse(null);
        if (proyecto != null) {
            proyecto.setTitulo(proyectoDetails.getTitulo());
            proyecto.setDescripcion(proyectoDetails.getDescripcion());
            // Agrega más campos aquí según tu modelo de datos
            return proyectoRepository.save(proyecto);
        }
        return null;
    }

    // Eliminar un proyecto (API REST)
    @DeleteMapping("/coordinador/proyectos/{id}")
    @ResponseBody
    public void deleteProyecto(@PathVariable int id) {
        proyectoRepository.deleteById(id);
    }

    // Buscar proyectos por cédula de estudiante (API REST)
    @GetMapping("/coordinador/proyectos/estudiante/{cedula}")
    @ResponseBody
    public List<Proyecto> getProyectosByEstudianteCedula(@PathVariable String cedula) {
        return proyectoService.findByEstudianteCedula(cedula);
    }
}
