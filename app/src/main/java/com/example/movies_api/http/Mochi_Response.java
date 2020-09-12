package com.example.movies_api.http;


import com.squareup.moshi.Json;

public class Mochi_Response {

    @Json(name = "poster_path")
    private String urlPoster;

    @Json(name = "original_title")
    private String tituloOriginal;

    @Json(name = "title")
    private String titulo;

    @Json(name = "genre_ids")
    private int[] genero;

    @Json(name = "release_date")
    private String data;

    @Json(name = "adult")
    private boolean adulto;

    @Json(name = "overview")
    private String overview;

    @Json(name = "original_language")
    private String language;

    @Json(name = "popularity")
    private float popularidade;

    @Json(name = "backdrop_path")
    private String urlPosterSecundario;

    public Mochi_Response(String urlPoster, String tituloOriginal, String titulo, int[] genero, String data, boolean adulto, String overview, String language, float popularidade, String urlPosterSecundario) {
        this.urlPoster = urlPoster;
        this.tituloOriginal = tituloOriginal;
        this.titulo = titulo;
        this.genero = genero;
        this.data = data;
        this.adulto = adulto;
        this.overview = overview;
        this.language = language;
        this.popularidade = popularidade;
        this.urlPosterSecundario = urlPosterSecundario;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public String getData() {
        return data;
    }

    public int[] getGenero() {
        return genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isAdulto() {
        return adulto;
    }

    public String getOverview() {
        return overview;
    }

    public String getLanguage() {
        return language;
    }

    public float getPopularidade() {
        return popularidade;
    }

    public String getUrlPosterSecundario() {
        return urlPosterSecundario;
    }
}
