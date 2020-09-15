package com.example.movies_api.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FilmeViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<FilmesEntity>> allFilmes;

    public FilmeViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allFilmes = repository.getAllFilmes();
    }

    public void insert(FilmesEntity filme) {
        repository.insert(filme);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public FilmesEntity getFilmeByTitle(String title) {
        return repository.getFilmeBytitle(title);
    }

    public void delete(FilmesEntity filme) {
        repository.delete(filme);
    }

    public LiveData<List<FilmesEntity>> getAllFilmes() {
        return allFilmes;
    }
}
