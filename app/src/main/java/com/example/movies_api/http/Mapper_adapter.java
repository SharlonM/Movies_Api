package com.example.movies_api.http;

import com.example.movies_api.model.Filme;

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
}
