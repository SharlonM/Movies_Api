package com.example.movies_api.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Filmes_Services {

    @GET("movie/popular")
    Call<Mochi_Result> getFilmesPopulares(@Query("api_key") String chaveApi, @Query("language") String language, @Query("page") int page);

    @GET("genre/movie/list")
    Call<Mochi_Genero> getGen(@Query("api_key") String chaveApi, @Query("language") String language);

}
