
package com.libreria.libreria.controladores;

import com.libreria.libreria.entidades.Editorial;
import com.libreria.libreria.servicios.EditorialServicio;
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
@RequestMapping("/editorial")
public class EditorialController {
    
    @Autowired
    private EditorialServicio editorialservicio;
    
    @GetMapping("/agregarEditorial")
    public String formulario(){
        return "agregarEditorial";
    }
    
    @GetMapping("/menuEditorial")
    public String menu(){
        return "menuEditorial";
    }
    
    @PostMapping("/agregarEditorial")
    public String guardar(ModelMap modelo, @RequestParam String nombre){
        
        try {
            
            editorialservicio.guardarEditorial(nombre);
            modelo.put("exito", "Guardado completado");
            return "agregarEditorial";
            
        } catch (Exception e) {
            
            modelo.put("error", "Falto algun dato");
            return "agregarEditorial";
            
        }
        
    }
    
    @GetMapping("/listarEditoriales")
    public String lista(ModelMap modelo) throws Exception{
        
        List<Editorial> editorialesLista = editorialservicio.listarEditoriales();
        
        modelo.addAttribute("editoriales", editorialesLista);
        
        return "listarEditoriales";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        modelo.put("editorial", editorialservicio.getOne(id));
        return "modificarEditorial";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String nombre){
        
        try {
            
            editorialservicio.modificarEditorial(id, nombre);
            modelo.put("exito", "Modificacion exitosa");
            return "exitoEditorial";
            
        } catch (Exception e) {
            
            modelo.put("error", "Falto algun dato");
            
            return "modificarEditorial";
            
        }
    }
    
    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id, ModelMap modelo){
        modelo.put("editorial", editorialservicio.getOne(id));
        return "exitoEditorial";
    }
    
    @PostMapping("/baja/{id}")
    public String baja(ModelMap modelo, @PathVariable String id){
        
        try {
            
            editorialservicio.darDeBajaEditorial(id);
            modelo.put("exito", "Se ha dado de baja correctamente");
            
            return "exitoEditorial";
            
        } catch (Exception e) {
            
            modelo.put("error", "Algo fallo...");
            
            return "exitoEditorial";
            
        }
        
    }
    
    @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id, ModelMap modelo){
        modelo.put("editorial", editorialservicio.getOne(id));
        return "exitoEditorial";
    }
    
    @PostMapping("/alta/{id}")
    public String alta(ModelMap modelo, @PathVariable String id){
        
        try {
            
            editorialservicio.darDeAltaEditorial(id);
            modelo.put("exito", "Se ha dado de alta correctamente");
            
            return "exitoEditorial";
            
        } catch (Exception e) {
            
            modelo.put("error", "Algo fallo...");
            
            return "exitoEditorial";
            
        }
        
    }
}
