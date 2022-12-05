
package com.libreria.libreria.controladores;

import com.libreria.libreria.entidades.Autor;
import com.libreria.libreria.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")
public class AutorController {
    
    @Autowired
    private AutorServicio autorservicio;
    
    @GetMapping("/agregarAutor")
    public String formulario(){
        return "agregarAutor";
    }
    
    @GetMapping("/menuAutor")
    public String menu(){
        return "menuAutor";
    }
    
    @PostMapping("/agregarAutor")
    public String guardar(ModelMap modelo, @RequestParam String nombre){
        
        try {
            
            autorservicio.guardarAutor(nombre);
            modelo.put("exito", "Guardado completado");
            return "agregarAutor";
            
        } catch (Exception e) {
            
            modelo.put("error", "Falto algun dato");
            return "agregarAutor";
            
        }
        
    }
    
    @GetMapping("/listarAutores")
    public String listar(ModelMap modelo) throws Exception{

        List<Autor> autoresLista = autorservicio.listarAutores();
        
        modelo.addAttribute("autores", autoresLista);
        
        return "listarAutores";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        modelo.put("autor", autorservicio.getOne(id));
        return "modificarAutor";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String nombre){
        
        try {
            
            autorservicio.modificarAutor(id, nombre);
            modelo.put("exito", "Modificacion exitosa");
            return "exitoAutor";
            
        } catch (Exception e) {
            
            modelo.put("error", "Falto algun dato");
            
            return "modificarAutor";
            
        }
    }
    
    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id, ModelMap modelo){
        modelo.put("autor", autorservicio.getOne(id));
        return "exitoAutor";
    }
    
    @PostMapping("/baja/{id}")
    public String baja(ModelMap modelo, @PathVariable String id){
        
        try {
            
            autorservicio.darDeBajaAutor(id);
            modelo.put("exito", "Se ha dado de baja correctamente");
            
            return "exitoAutor";
            
        } catch (Exception e) {
            
            modelo.put("error", "Algo fallo...");
            
            return "exitoAutor";
            
        }
        
    }
    
    @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id, ModelMap modelo){
        modelo.put("autor", autorservicio.getOne(id));
        return "exitoAutor";
    }
    
    @PostMapping("/alta/{id}")
    public String alta(ModelMap modelo, @PathVariable String id){
        
        try {
            
            autorservicio.darDeAltaAutor(id);
            modelo.put("exito", "Se ha dado de alta correctamente");
            
            return "exitoAutor";
            
        } catch (Exception e) {
            
            modelo.put("error", "Algo fallo...");
            
            return "exitoAutor";
            
        }
        
    }
    
}
