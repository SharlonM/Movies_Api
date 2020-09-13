package com.example.movies_api.http.series;

import com.squareup.moshi.Json;

import java.util.List;

public class Result_Series {

    @Json(name = "results")
    private final List<Mochi_series> resultSerie;

    @Json(name = "total_pages")
    private final int total_page;

    public Result_Series(List<Mochi_series> resultSerie, int total_page) {
        this.resultSerie = resultSerie;
        this.total_page = total_page;
    }

    public List<Mochi_series> getResultSerie() {
        return resultSerie;
    }

    public int getTotal_page() {
        return total_page;
    }
}
