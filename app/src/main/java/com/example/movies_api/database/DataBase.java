package com.example.movies_api.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {FilmesEntity.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public abstract FilmesDAO filmesDAO();

    private static DataBase INSTANCE;

    public static synchronized DataBase getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context,
                    DataBase.class,
                    "app_database"
            ).fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new DbAsync(INSTANCE).execute();
        }
    };

    private static class DbAsync extends AsyncTask<Void, Void, Void> {
        private FilmesDAO filmesDAO;

        public DbAsync(DataBase db) {
            this.filmesDAO = db.filmesDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //filmesDAO.insert();
            return null;
        }
    }
}
