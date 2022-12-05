
package com.libreria.libreria.repositorios;

import com.libreria.libreria.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    
    @Query("SELECT l FROM Libro l WHERE l.id = :id")
    Libro buscarLibroPorID(@Param("id") String identificacion);
    
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    Libro buscarLibroPorTitulo(String titulo);

}
