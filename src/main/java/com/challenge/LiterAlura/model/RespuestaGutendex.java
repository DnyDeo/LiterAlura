package com.challenge.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaGutendex {
    private List<DatosLibro> results;

    public List<DatosLibro> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Resultados: " + results;
    }
}