package com.example.movies_api.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_api.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class listaAdapter extends RecyclerView.Adapter<listaAdapter.viewHolder> {

    private List<Filme> filmes;
    private List<Generos> generos;
    private static ItemFilmeClick itemFilmeClick;

    public listaAdapter(ItemFilmeClick item) {
        itemFilmeClick = item;
        filmes = new ArrayList<>();
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes.addAll(filmes);
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

        holder.bind(filmes.get(position), generos);
    }

    @Override
    public int getItemCount() {
        return (filmes != null && filmes.size() != 0) ? filmes.size() : 0;
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
        private List<Generos> gen;

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
                        itemFilmeClick.onItemFilmeClicado(movie, gen);
                    }
                }
            });

        }

        public void bind(Filme filme, List<Generos> gener) {
            this.movie = filme;
            this.gen = gener;
            int[] gen = filme.getGenero();
            String generoTexto = "";
            for (int i = 0; i < gener.size(); i++) {
                if (gener.get(i).getId() == gen[0]) {
                    generoTexto = gener.get(i).getGenero();
                }
            }

            text_TituloFilme.setText(filme.getTitulo());
            text_genero.setText(generoTexto);
            text_data.setText(filme.getData());
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + filme.getUrlPoster())
                    .into(imagePoster);
        }
    }

    public interface ItemFilmeClick {
        void onItemFilmeClicado(Filme filme, List<Generos> gen);
    }
}
