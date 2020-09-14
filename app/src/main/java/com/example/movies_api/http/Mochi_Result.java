package com.example.movies_api.http;

import com.example.movies_api.http.filmes.Filmes_Response;
import com.squareup.moshi.Json;

import java.util.List;

public class Mochi_Result {  // classe para tratar o json principal da api, pegando somente seu objeto results e total de paginas

    @Json(name = "results")
    private final List<Filmes_Response> results;

    public Mochi_Result(List<Filmes_Response> results, int total_pages) {
        this.results = results;
        this.total_pages = total_pages;
    }

    public List<Filmes_Response> getResults() {
        return results;
    }

    @Json(name = "total_pages")
    private final int total_pages;

    public int getTotal_pages() {
        return total_pages;
    }

}
