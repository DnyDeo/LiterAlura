package com.challenge.LiterAlura.repository;

import com.challenge.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTitulo(String titulo);
    @Query("SELECT DISTINCT l.idiomas FROM Libro l")
    List<String> findIdiomasDisponibles();
}
