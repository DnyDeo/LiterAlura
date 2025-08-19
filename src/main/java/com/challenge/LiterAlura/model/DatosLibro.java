
package com.challenge.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro {
    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<DatosAutor> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    public String getTitulo() {
        return titulo;
    }

    public List<DatosAutor> getAutores() {
        return autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    @Override
    public String toString() {
        return """
           📖 Título: %s
           ✍️ Autores: %s
           🌐 Idiomas: %s
           """.formatted(titulo, autores, idiomas);

    }
}