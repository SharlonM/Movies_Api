package com.example.movies_api.model;

import java.io.Serializable;

public class Filme implements Serializable {  // modelo de Filme serializado para troca de activitys
    private final String tituloOriginal;
    private final String urlPoster;
    private final String titulo;
    private final int[] genero;
    private final String data;
    private final boolean adulto;
    private final String overview;
    private final String language;
    private final float popularidade;
    private final String urlPosterSecundario;

    public Filme(String titulo, String poster, String titulo1, int[] genero, String data, boolean adulto, String overview, String language, float popularidade, String urlPosterSecundario) {
        this.tituloOriginal = titulo1;
        this.urlPoster = poster;
        this.titulo = titulo;
        this.genero = genero;
        this.data = data;
        this.adulto = adulto;
        this.overview = overview;
        this.language = language;
        this.popularidade = popularidade;
        this.urlPosterSecundario = urlPosterSecundario;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public int[] getGenero() {
        return genero;
    }

    public String getData() {
        return data;
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
