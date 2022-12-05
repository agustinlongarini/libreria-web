
package com.libreria.libreria.servicios;

import com.libreria.libreria.entidades.Libro;
import com.libreria.libreria.repositorios.AutorRepositorio;
import com.libreria.libreria.repositorios.EditorialRepositorio;
import com.libreria.libreria.repositorios.LibroRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {
    
    @Autowired
    private LibroRepositorio librorepo;
    
    @Autowired
    private AutorRepositorio autorrepo;
    
    @Autowired
    private EditorialRepositorio editorialrepo;
    
    @Transactional
    public void guardarLibro (Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, String autor, String editorial) throws Exception {
        
        validar(isbn, titulo, anio, ejemplares, ejemplaresPrestados, autor, editorial);
        
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplares-ejemplaresPrestados);
        libro.setAlta(true);
        libro.setAutor(autorrepo.buscarAutorPorID(autor));
        libro.setEditorial(editorialrepo.buscarEditorialPorID(editorial));
        
        librorepo.save(libro);
        
    }
    
    @Transactional
    public void modificarLibro(String id, Long isbn, String nuevoTitulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, String autor, String editorial) throws Exception {
        
        validar(isbn, nuevoTitulo, anio, ejemplares, ejemplaresPrestados, autor, editorial);
        
        Libro libro = librorepo.buscarLibroPorID(id);
        if(libro != null){     
            libro.setIsbn(isbn);
            libro.setTitulo(nuevoTitulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplares-ejemplaresPrestados);
            libro.setAutor(autorrepo.buscarAutorPorID(autor));
            libro.setEditorial(editorialrepo.buscarEditorialPorID(editorial));
        
            librorepo.save(libro);
        } else {
            throw new Exception("No se encontro el libro solicitado");
        }
        
    }
    
    @Transactional
    public void darDeBajaLibro(String id) throws Exception {
        
        Libro libro = librorepo.buscarLibroPorID(id);
        if(libro != null){     
            libro.setAlta(false);
            
            librorepo.save(libro);
        } else {
            throw new Exception("No se encontro el libro solicitado");
        }
        
    }
    
    @Transactional
    public void darDeAltaLibro(String id) throws Exception {
        
        Libro libro = librorepo.buscarLibroPorID(id);
        if(libro != null){     
            libro.setAlta(true);
            
            librorepo.save(libro);
        } else {
            throw new Exception("No se encontro el libro solicitado");
        }
        
    }
    
    public void validar(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, String autor, String editorial) throws Exception {
        
        if(isbn == null){
            throw new Exception("Debe ingresar un codigo isbn");
        }
        
        if((titulo == null)||(titulo.isEmpty())){
            throw new Exception("Debe ingresar un titulo");
        }
        
        if((anio < 0)||(anio == null)){
            throw new Exception("Debe ingresar un anio correcto");
        }
        
        if((ejemplares < 0 )||(ejemplares == null) ){
            throw new Exception("Debe ingresar un numero de ejemplares correcto");
        }
        
        if((ejemplaresPrestados < 0)||(ejemplaresPrestados == null)){
            throw new Exception("Debe ingresar un numero de ejemplares correcto");
        }
        
        if((autor == null)||(autor.isEmpty())){
            throw new Exception("Debe ingresar un autor");
        }
        
        if((editorial == null)||(editorial.isEmpty())){
            throw new Exception("Debe ingresar una editorial");
        }
        
    }
    
    @Transactional
    public List<Libro> listarLibros() throws Exception {
        
        List<Libro> libros = librorepo.findAll();
        
        if(libros.isEmpty()){
            throw new Exception("No hay libros guardados");
        }
        
        return libros;
        
    }
    
    @Transactional
    public Libro getOne(String id){
        return librorepo.buscarLibroPorID(id);
    }
    
}
