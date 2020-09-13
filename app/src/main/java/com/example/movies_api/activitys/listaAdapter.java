package com.example.movies_api.activitys;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_api.R;
import com.example.movies_api.model.Filme;
import com.example.movies_api.model.Generos;
import com.example.movies_api.model.Series;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class listaAdapter extends RecyclerView.Adapter<listaAdapter.viewHolder> {

    private List<Filme> filmes;
    private static List<Generos> generos;
    private static ItemFilmeClick itemFilmeClick;
    private List<Series> series;
    private static boolean choice;

    public listaAdapter(ItemFilmeClick item, boolean b) {
        itemFilmeClick = item;
        filmes = new ArrayList<>();
        series = new ArrayList<>();
        choice = b;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes.addAll(filmes);
        notifyDataSetChanged();
    }

    public void setSeries(List<Series> s) {
        this.series.addAll(s);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filme, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        if (choice) {
            holder.bind(filmes.get(position), generos);
        } else {
            holder.series(series.get(position), generos);
        }
    }

    @Override
    public int getItemCount() {

        if (choice) {
            return (filmes != null && filmes.size() != 0) ? filmes.size() : 0;
        } else {
            return (series != null && series.size() != 0) ? series.size() : 0;
        }


    }

    public void setGeneros(List<Generos> generos) {
        this.generos = generos;
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        private TextView text_TituloFilme;
        private TextView text_data;
        private TextView text_genero;
        private ImageView imagePoster;
        private Filme movie;
        private Series serie;
        private static List<Generos> gen;
        String generoTexto = "carregando";

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            text_TituloFilme = itemView.findViewById(R.id.txt_titulo_filme);
            text_data = itemView.findViewById(R.id.txt_data_filme);
            text_genero = itemView.findViewById(R.id.txt_genero_filme);
            imagePoster = itemView.findViewById(R.id.image_poster);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemFilmeClick != null) {
                        itemFilmeClick.onItemFilmeClicado(movie, gen, serie);
                    }
                }
            });

        }

        public void bind(Filme filme, List<Generos> gener) {
            this.movie = filme;
            gen = gener;
            int[] gen = filme.getGenero();
            try {
                for (int i = 0; i < gener.size(); i++) {
                    if (gener.get(i).getId() == gen[0]) {
                        generoTexto = gener.get(i).getGenero();
                    }
                }
            } catch (Exception e) {
                System.out.println("FalhA" + e);
            }

            text_TituloFilme.setText(filme.getTitulo());
            text_genero.setText(generoTexto);
            text_data.setText(filme.getData());
            if (filme.getUrlPoster() == null) {
                imagePoster.setImageResource(R.drawable.no_image);
            } else {
                Picasso.get().load("https://image.tmdb.org/t/p/w500/" + filme.getUrlPoster())
                        .into(imagePoster);
            }
        }

        public void series(Series series, List<Generos> generos) {
            this.serie = series;
            gen = generos;
            int[] genSerie = serie.getGeneros();
            try {
                for (int i = 0; i < generos.size(); i++) {
                    if (generos.get(i).getId() == genSerie[0]) {
                        generoTexto = generos.get(i).getGenero();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            text_TituloFilme.setText(series.getNome());
            text_genero.setText(generoTexto);
            text_data.setText(series.getDate());
            if (series.getUrlPoster() == null) {
                imagePoster.setImageResource(R.drawable.no_image);
            } else {
                Picasso.get().load("https://image.tmdb.org/t/p/w500/" + series.getUrlPoster())
                        .into(imagePoster);
            }
        }
    }

    public interface ItemFilmeClick {
        void onItemFilmeClicado(Filme filme, List<Generos> gen, Series serie);
    }
}
