
package com.libreria.libreria.repositorios;

import com.libreria.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    
   @Query("SELECT a FROM Autor a WHERE a.id = :id")
   Autor buscarAutorPorID(@Param("id") String identificacion);
   
   @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
   Autor buscarAutorPorNombre(@Param("nombre") String autor);
   
}
