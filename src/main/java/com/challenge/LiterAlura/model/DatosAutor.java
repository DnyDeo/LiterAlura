package com.challenge.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosAutor {
    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    private Integer fechaDeNacimiento;

    @JsonAlias("death_year")
    private Integer fechaDeFallecimiento;

    public String getNombre() {
        return nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    @Override
    public String toString() {
        return nombre + " (" + fechaDeNacimiento + " - " + fechaDeFallecimiento + ")";
    }
}