package com.example.movies_api.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Filmes")
public class FilmesEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id = 0;

    String tituloOriginal;
    String urlPoster;
    String titulo;
    String genero;
    String data;
    boolean adulto;
    String overview;
    String language;
    float popularidade;
    String urlPosterSecundario;

    public FilmesEntity(String tituloOriginal, String urlPoster, String titulo,
                        String genero, String data, boolean adulto, String overview,
                        String language, float popularidade, String urlPosterSecundario) {

        this.tituloOriginal = tituloOriginal;
        this.urlPoster = urlPoster;
        this.titulo = titulo;
        this.genero = genero;
        this.data = data;
        this.adulto = adulto;
        this.overview = overview;
        this.language = language;
        this.popularidade = popularidade;
        this.urlPosterSecundario = urlPosterSecundario;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isAdulto() {
        return adulto;
    }

    public void setAdulto(boolean adulto) {
        this.adulto = adulto;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public float getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(float popularidade) {
        this.popularidade = popularidade;
    }

    public String getUrlPosterSecundario() {
        return urlPosterSecundario;
    }

    public void setUrlPosterSecundario(String urlPosterSecundario) {
        this.urlPosterSecundario = urlPosterSecundario;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }


}
