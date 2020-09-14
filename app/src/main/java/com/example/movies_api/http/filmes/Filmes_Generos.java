package com.example.movies_api.http.filmes;

import com.example.movies_api.model.Generos;
import com.squareup.moshi.Json;

import java.util.List;

public class Filmes_Generos {  // recebe a lista de generos dos filmes da api

    public Filmes_Generos(List<Generos> generos) {
        this.generos = generos;
    }

    @Json(name = "genres")
    private final List<Generos> generos;

    public List<Generos> getGeneros() {
        return generos;
    }
}
