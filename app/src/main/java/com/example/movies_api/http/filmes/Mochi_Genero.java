package com.example.movies_api.http.filmes;

import com.example.movies_api.model.Generos;
import com.squareup.moshi.Json;

import java.util.List;

public class Mochi_Genero {

    public Mochi_Genero(List<Generos> generos) {
        this.generos = generos;
    }

    @Json(name = "genres")
    private final List<Generos> generos;

    public List<Generos> getGeneros() {
        return generos;
    }
}
