package com.challenge.LiterAlura.repository;

import com.challenge.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("""
           SELECT a 
           FROM Autor a 
           WHERE a.fechaDeNacimiento <= :anio 
             AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento >= :anio)
           """)
    List<Autor> findAutoresVivosEnAnio(@Param("anio") int anio);
}
