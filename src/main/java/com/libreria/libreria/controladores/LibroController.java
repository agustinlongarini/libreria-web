
package com.libreria.libreria.controladores;

import com.libreria.libreria.entidades.Autor;
import com.libreria.libreria.entidades.Editorial;
import com.libreria.libreria.entidades.Libro;
import com.libreria.libreria.servicios.AutorServicio;
import com.libreria.libreria.servicios.EditorialServicio;
import com.libreria.libreria.servicios.LibroServicio;
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
@RequestMapping("/libro")
public class LibroController {
    
    @Autowired
    private LibroServicio libroservicio;
    
    @Autowired
    private EditorialServicio editorialservicio;
    
    @Autowired
    private AutorServicio autorservicio;
    
    @GetMapping("/agregarLibro")
    public String formulario(ModelMap modelo) throws Exception{
        
        List<Autor> autoresLista = autorservicio.listarAutores();
 
        modelo.addAttribute("autores", autoresLista);
        
        List<Editorial> editorialesLista = editorialservicio.listarEditoriales();
        
        modelo.addAttribute("editoriales", editorialesLista);
        
        return "agregarLibro";
    }
    
    @GetMapping("/menuLibro")
    public String menu(){
        return "menuLibro";
    }
    
    @PostMapping("/agregarLibro")
    public String guardar(ModelMap modelo, @RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam String autor, @RequestParam String editorial){
        
        try {
            
            libroservicio.guardarLibro(isbn, titulo, anio, ejemplares, ejemplaresPrestados, autor, editorial);
            modelo.put("Exito", "Guardado completado");
            return "agregarLibro";
            
        } catch (Exception e) {
            
            modelo.put("Error", "Falto algun dato");
            return "agregarLibro";
            
        }
        
    }
    
    @GetMapping("/listarLibros")
    public String lista(ModelMap modelo) throws Exception{
        
        List<Libro> librosLista = libroservicio.listarLibros();
        
        modelo.addAttribute("libros", librosLista);
        
        return "listarLibros";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) throws Exception{
        
        List<Autor> autoresLista = autorservicio.listarAutores();
 
        modelo.addAttribute("autores", autoresLista);
        
        List<Editorial> editorialesLista = editorialservicio.listarEditoriales();
        
        modelo.addAttribute("editoriales", editorialesLista);
        
        modelo.put("libro", libroservicio.getOne(id));
        
        return "modificarLibro";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam Long isbn, @RequestParam String nuevoTitulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam String autor, @RequestParam String editorial){
        
        try {
            
            libroservicio.modificarLibro(id, isbn, nuevoTitulo, anio, ejemplares, ejemplaresPrestados, autor, editorial);
            modelo.put("exito", "Modificacion exitosa");
            return "exitoLibro";
            
        } catch (Exception e) {
            
            modelo.put("error", "Falto algun dato");
            
            return "modificarLibro";
            
        }
    }
    
    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id, ModelMap modelo){
        modelo.put("libro", libroservicio.getOne(id));
        return "exitoLibro";
    }
    
    @PostMapping("/baja/{id}")
    public String baja(ModelMap modelo, @PathVariable String id){
        
        try {
            
            libroservicio.darDeBajaLibro(id);
            modelo.put("exito", "Se ha dado de baja correctamente");
            
            return "exitoLibro";
            
        } catch (Exception e) {
            
            modelo.put("error", "Algo fallo...");
            
            return "exitoLibro";
            
        }
        
    }
    
    @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id, ModelMap modelo){
        modelo.put("libro", libroservicio.getOne(id));
        return "exitoLibro";
    }
    
    @PostMapping("/alta/{id}")
    public String alta(ModelMap modelo, @PathVariable String id){
        
        try {
            
            libroservicio.darDeAltaLibro(id);
            modelo.put("exito", "Se ha dado de alta correctamente");
            
            return "exitoLibro";
            
        } catch (Exception e) {
            
            modelo.put("error", "Algo fallo...");
            
            return "exitoLibro";
            
        }
        
    }
    
}
