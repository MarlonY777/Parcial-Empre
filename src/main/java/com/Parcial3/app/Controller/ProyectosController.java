package com.Parcial3.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProyectosController {

    @GetMapping("/proyectosAsignados")
    public String mostrarProyectosAsignados() {
        return "proyectosAsignados";
    }
}
