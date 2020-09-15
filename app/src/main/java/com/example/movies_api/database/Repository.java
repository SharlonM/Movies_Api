package com.example.movies_api.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private FilmesDAO filmesDAO;
    private LiveData<List<FilmesEntity>> allFilmes;
    private FilmesEntity filmeBytitle;

    public Repository(Application application) {
        DataBase dataBase = DataBase.getInstance(application);
        filmesDAO = dataBase.filmesDAO();
        allFilmes = filmesDAO.getAll();
    }

    public void insert(FilmesEntity filme) {
        new InsertFilmeAsync(filmesDAO).execute(filme);
    }

    public void delete(FilmesEntity filme) {
        new DeleteFilmeAsync(filmesDAO).execute(filme);
    }

    public void deleteAll() {
        new DeleteAllFilmeAsync(filmesDAO).execute();
    }

    public LiveData<List<FilmesEntity>> getAllFilmes() {
        return allFilmes;
    }

    public FilmesEntity getFilmeBytitle(String title) {
        filmeBytitle = filmesDAO.getFilme(title);
        return filmeBytitle;
    }

    private static class InsertFilmeAsync extends AsyncTask<FilmesEntity, Void, Void> {

        private FilmesDAO filmesDAO;

        public InsertFilmeAsync(FilmesDAO dao) {
            this.filmesDAO = dao;
        }

        @Override
        protected Void doInBackground(FilmesEntity... filmesEntities) {
            filmesDAO.insert(filmesEntities[0]);
            return null;
        }
    }

    private static class DeleteFilmeAsync extends AsyncTask<FilmesEntity, Void, Void> {

        private FilmesDAO filmesDAO;

        public DeleteFilmeAsync(FilmesDAO dao) {
            this.filmesDAO = dao;
        }

        @Override
        protected Void doInBackground(FilmesEntity... filmesEntities) {
            filmesDAO.delete(filmesEntities[0].id);
            return null;
        }
    }

    private static class DeleteAllFilmeAsync extends AsyncTask<FilmesEntity, Void, Void> {

        private FilmesDAO filmesDAO;

        public DeleteAllFilmeAsync(FilmesDAO dao) {
            this.filmesDAO = dao;
        }

        @Override
        protected Void doInBackground(FilmesEntity... filmesEntities) {
            filmesDAO.deleteAll();
            return null;
        }
    }

}
