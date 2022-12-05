
package com.libreria.libreria.repositorios;

import com.libreria.libreria.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, Long> {
    
    @Query("SELECT e FROM Editorial e WHERE e.id = :id")
    Editorial buscarEditorialPorID(@Param("id") String identificacion);
    
    @Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
    Editorial buscarEditorialPorNombre(@Param("nombre") String editorial);
    
}
