package com.example.movies_api.http.series;

import com.squareup.moshi.Json;

public class Mochi_series {
    public Mochi_series(String ulr_poster, float popularidade, String urlPosterSecundario, String overview, String data, String language, int[] generos, String nome, String nome_original) {
        this.ulr_poster = ulr_poster;
        this.popularidade = popularidade;
        this.urlPosterSecundario = urlPosterSecundario;
        this.overview = overview;
        this.data = data;
        this.language = language;
        this.generos = generos;
        this.nome = nome;
        this.nome_original = nome_original;
    }

    @Json(name = "poster_path")
    private String ulr_poster;

    @Json(name = "popularity")
    private float popularidade;

    @Json(name = "backdrop_path")
    private String urlPosterSecundario;

    @Json(name = "overview")
    private String overview;

    @Json(name = "first_air_date")
    private String data;

    @Json(name = "original_language")
    private String language;

    @Json(name = "genre_ids")
    private int[] generos;

    @Json(name = "name")
    private String nome;

    @Json(name = "original_name")
    private String nome_original;

    public String getUlr_poster() {
        return ulr_poster;
    }

    public float getPopularidade() {
        return popularidade;
    }

    public String getUrlPosterSecundario() {
        return urlPosterSecundario;
    }

    public String getOverview() {
        return overview;
    }

    public String getData() {
        return data;
    }

    public String getLanguage() {
        return language;
    }

    public int[] getGeneros() {
        return generos;
    }

    public String getNome() {
        return nome;
    }

    public String getNome_original() {
        return nome_original;
    }
}
