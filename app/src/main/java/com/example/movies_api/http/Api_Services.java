package com.example.movies_api.http;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Api_Services {
    private static Filmes_Services INSTANCE;

    public static Filmes_Services getInstance() {

        if (INSTANCE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();

            INSTANCE = retrofit.create(Filmes_Services.class);
        }

        return INSTANCE;

    }
}
