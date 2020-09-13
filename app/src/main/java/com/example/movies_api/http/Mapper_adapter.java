package com.example.movies_api.http;

import com.example.movies_api.http.filmes.Mochi_Response;
import com.example.movies_api.http.series.Mochi_series;
import com.example.movies_api.model.Filme;
import com.example.movies_api.model.Series;

import java.util.ArrayList;
import java.util.List;

public class Mapper_adapter {
    public static List<Filme> responseParse(List<Mochi_Response> mochiResponseList) {
        List<Filme> listFilme = new ArrayList<>();

        for (Mochi_Response listResponse : mochiResponseList) {
            final Filme filme = new Filme(
                    listResponse.getTitulo(),
                    listResponse.getUrlPoster(),
                    listResponse.getTituloOriginal(),
                    listResponse.getGenero(),
                    listResponse.getData(),
                    listResponse.isAdulto(),
                    listResponse.getOverview(),
                    listResponse.getLanguage(),
                    listResponse.getPopularidade(),
                    listResponse.getUrlPosterSecundario());
            listFilme.add(filme);
        }
        return listFilme;
    }

    public static List<Series> seriesParse(List<Mochi_series> lista) {
        List<Series> listSeries = new ArrayList<>();

        for (Mochi_series listResponse : lista) {
            final Series serie = new Series(
                    listResponse.getNome(),
                    listResponse.getNome_original(),
                    listResponse.getData(),
                    listResponse.getLanguage(),
                    listResponse.getOverview(),
                    listResponse.getGeneros(),
                    listResponse.getUlr_poster(),
                    listResponse.getUrlPosterSecundario(),
                    listResponse.getPopularidade()
            );
            listSeries.add(serie);
        }

        return listSeries;
    }
}
