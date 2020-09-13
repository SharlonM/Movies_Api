package com.example.movies_api.http;

import com.example.movies_api.http.filmes.Mochi_Response;
import com.squareup.moshi.Json;

import java.util.List;

public class Mochi_Result {

    @Json(name = "results")
    private final List<Mochi_Response> results;

    public Mochi_Result(List<Mochi_Response> results, int total_pages) {
        this.results = results;
        this.total_pages = total_pages;
    }

    public List<Mochi_Response> getResults() {
        return results;
    }

    @Json(name = "total_pages")
    private final int total_pages;

    public int getTotal_pages() {
        return total_pages;
    }

}
