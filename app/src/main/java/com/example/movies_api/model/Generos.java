package com.example.movies_api.model;

import com.squareup.moshi.Json;

public class Generos {

    @Json(name = "id")
    private int id;

    @Json(name = "name")
    private String genero;

    public Generos(int id, String genero) {
        this.id = id;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public String getGenero() {
        return genero;
    }
}
