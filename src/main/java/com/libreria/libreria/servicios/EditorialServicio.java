
package com.libreria.libreria.servicios;

import com.libreria.libreria.entidades.Editorial;
import com.libreria.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialrepo;
    
    @Transactional
    public void guardarEditorial (String nombre) throws Exception {
        
        validar(nombre);
        
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        
        editorialrepo.save(editorial);
        
    }
    
    @Transactional
    public void modificarEditorial (String id, String nuevoNombre) throws Exception {
        
        Editorial editorial = editorialrepo.buscarEditorialPorID(id);
        if(editorial != null){
            editorial.setNombre(nuevoNombre);
            
            editorialrepo.save(editorial);
        } else {
            throw new Exception("No se encontro la editorial solicitada");
        }
        
    }
    
    @Transactional
    public void darDeBajaEditorial(String id) throws Exception {
        
        Editorial editorial = editorialrepo.buscarEditorialPorID(id);
        if(editorial != null){
            editorial.setAlta(false);
            
            editorialrepo.save(editorial);
        } else {
            throw new Exception("No se encontro la editorial solicitada");
        }
        
    }
    
    @Transactional
    public void darDeAltaEditorial(String id) throws Exception {
        
        Editorial editorial = editorialrepo.buscarEditorialPorID(id);
        if(editorial != null){
            editorial.setAlta(true);
            
            editorialrepo.save(editorial);
        } else {
            throw new Exception("No se encontro la editorial solicitada");
        }
        
    }
    
    public void validar(String nombre) throws  Exception {
        
        if((nombre == null) || (nombre.isEmpty())){
            throw new Exception("Debe ingresar el nombre de la editorial");
        }
        
    }
    
    @Transactional
    public List<Editorial> listarEditoriales() throws Exception {
        
        List<Editorial> editoriales = editorialrepo.findAll();
        
        if(editoriales.isEmpty()){
            throw new Exception("No hay editoriales guardadas");
        }
        
        return editoriales;
        
    }
    
    @Transactional
    public Editorial getOne(String id){
        return editorialrepo.buscarEditorialPorID(id);
    }
    
}
