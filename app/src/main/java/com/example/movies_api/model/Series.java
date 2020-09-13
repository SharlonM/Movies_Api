package com.example.movies_api.model;

import java.io.Serializable;

public class Series implements Serializable {
    private String nome;
    private String nome_original;
    private String date;
    private String language;
    private String overview;
    private int[] generos;
    private String urlPoster;
    private String urlPosterOriginal;
    private float popularidade;

    public Series(String nome, String nome_original, String date, String language, String overview, int[] generos, String urlPoster, String urlPosterOriginal, float popularidade) {
        this.nome = nome;
        this.nome_original = nome_original;
        this.date = date;
        this.language = language;
        this.overview = overview;
        this.generos = generos;
        this.urlPoster = urlPoster;
        this.urlPosterOriginal = urlPosterOriginal;
        this.popularidade = popularidade;
    }

    public String getNome() {
        return nome;
    }

    public String getNome_original() {
        return nome_original;
    }

    public String getDate() {
        return date;
    }

    public String getLanguage() {
        return language;
    }

    public String getOverview() {
        return overview;
    }

    public int[] getGeneros() {
        return generos;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public String getUrlPosterOriginal() {
        return urlPosterOriginal;
    }

    public float getPopularidade() {
        return popularidade;
    }
}
