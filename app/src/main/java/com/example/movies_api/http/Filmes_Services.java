package com.example.movies_api.http;

import com.example.movies_api.http.filmes.Filmes_Generos;
import com.example.movies_api.http.series.Result_Series;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Filmes_Services {  // interface para uso dos gets de cada tipo da api

    @GET("movie/popular")
    Call<Mochi_Result> getFilmesPopulares(@Query("api_key") String chaveApi, @Query("language") String language, @Query("page") int page);

    @GET("movie/now_playing")
    Call<Mochi_Result> getFilmesCartaz(@Query("api_key") String chaveApi, @Query("language") String language, @Query("page") int page, @Query("region") String region);

    @GET("genre/movie/list")
    Call<Filmes_Generos> getGen(@Query("api_key") String chaveApi, @Query("language") String language);

    @GET("tv/popular")
    Call<Result_Series> getSeries(@Query("api_key") String chaveApi, @Query("language") String language, @Query("page") int page);

    @GET("genre/tv/list")
    Call<Filmes_Generos> getGenSeries(@Query("api_key") String chaveApi, @Query("language") String language);

}
