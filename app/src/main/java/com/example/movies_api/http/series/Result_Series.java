package com.example.movies_api.http.series;

import com.squareup.moshi.Json;

import java.util.List;

public class Result_Series {  // tratar o json recebido da api, pegando apenas o array resultado e total de paginas

    @Json(name = "results")
    private final List<Series_Response> resultSerie;

    @Json(name = "total_pages")
    private final int total_page;

    public Result_Series(List<Series_Response> resultSerie, int total_page) {
        this.resultSerie = resultSerie;
        this.total_page = total_page;
    }

    public List<Series_Response> getResultSerie() {
        return resultSerie;
    }

    public int getTotal_page() {
        return total_page;
    }
}
