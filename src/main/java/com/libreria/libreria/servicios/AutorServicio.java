
package com.libreria.libreria.servicios;

import com.libreria.libreria.entidades.Autor;
import com.libreria.libreria.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio autorrepo;
    
    @Transactional
    public void guardarAutor (String nombre) throws Exception {
        
        validar(nombre);
        
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);
        
        autorrepo.save(autor);
        
    }
    
    @Transactional
    public void modificarAutor (String id, String nuevoNombre) throws Exception {
        
        Autor autor = autorrepo.buscarAutorPorID(id);
        if(autor != null){
            autor.setNombre(nuevoNombre);
            
            autorrepo.save(autor);
        } else {
            throw new Exception("No se encontro el autor solicitado");
        }
        
    }
    
    @Transactional
    public void darDeBajaAutor(String id) throws Exception {
        
        Autor autor = autorrepo.buscarAutorPorID(id);
        if(autor != null){
            autor.setAlta(false);
            
            autorrepo.save(autor);
        } else {
            throw new Exception("No se encontro el autor solicitado");
        }
        
    }
    
    @Transactional
    public void darDeAltaAutor(String id) throws Exception {
        
        Autor autor = autorrepo.buscarAutorPorID(id);
        if(autor != null){
            autor.setAlta(true);
            
            autorrepo.save(autor);
        } else {
            throw new Exception("No se encontro el autor solicitado");
        }
        
    }
    
    public void validar(String nombre) throws  Exception {
        
        if((nombre == null) || (nombre.isEmpty())){
            throw new Exception("Debe ingresar el nombre del autor");
        }
        
    }
    
    @Transactional
    public List<Autor> listarAutores() throws Exception {
        
        List<Autor> autores = autorrepo.findAll();
        
        if(autores.isEmpty()){
            throw new Exception("No hay editoriales guardadas");
        }
        
        return autores;
        
    }
    
    @Transactional
    public Autor getOne(String id){
        return autorrepo.buscarAutorPorID(id);
    }
}
