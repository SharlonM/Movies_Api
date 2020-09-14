package com.example.movies_api.http;

import com.example.movies_api.http.filmes.Filmes_Response;
import com.example.movies_api.http.series.Series_Response;
import com.example.movies_api.model.Filme;
import com.example.movies_api.model.Series;

import java.util.ArrayList;
import java.util.List;

public class Mapper_adapter {
    public static List<Filme> responseParse(List<Filmes_Response> mochiResponseList) { // classe que faz as converções da lib para objeto
        List<Filme> listFilme = new ArrayList<>();

        for (Filmes_Response listResponse : mochiResponseList) {
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

    public static List<Series> seriesParse(List<Series_Response> lista) {
        List<Series> listSeries = new ArrayList<>();

        for (Series_Response listResponse : lista) {
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
