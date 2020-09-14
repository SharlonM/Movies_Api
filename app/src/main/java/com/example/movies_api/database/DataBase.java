package com.example.movies_api.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {FilmesEntity.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract FilmesDAO filmesDAO();

    private static DataBase INSTANCE;

    public static synchronized DataBase getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context,
                    DataBase.class,
                    "app_database"
            ).build();
        }
        return INSTANCE;

    }
}
