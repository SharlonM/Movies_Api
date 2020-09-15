package com.example.movies_api.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FilmesDAO {

    @Insert
    void insert(FilmesEntity... filmes);

    @Update
    void update(FilmesEntity... filmes);

    @Query(value = "DELETE FROM Filmes WHERE id = :id")
    void delete(int id);

    @Query(value = "DELETE FROM Filmes")
    void deleteAll();

    @Query("SELECT * FROM Filmes")
    LiveData<List<FilmesEntity>> getAll();

    @Query("SELECT * FROM Filmes WHERE titulo = :x")
    FilmesEntity getFilme(String x);

}
