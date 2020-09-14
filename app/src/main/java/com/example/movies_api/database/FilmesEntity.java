package com.example.movies_api.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Filmes")
public class FilmesEntity {

    @PrimaryKey(autoGenerate = true)
    public int id = 0;

    String tituloOriginal;
    String urlPoster;
//    String titulo;
//    int[] genero;
//    String data;
//    boolean adulto;
//    String overview;
//    String language;
//    float popularidade;
//    String urlPosterSecundario;

}
