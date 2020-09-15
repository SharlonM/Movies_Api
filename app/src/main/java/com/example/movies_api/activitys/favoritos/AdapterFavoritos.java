package com.example.movies_api.activitys.favoritos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_api.R;
import com.example.movies_api.database.FilmesEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterFavoritos extends RecyclerView.Adapter<AdapterFavoritos.FavoritosHolder> {

    private List<FilmesEntity> filmes;
    private static ItemFilmeClick itemFilmeClick;

    public AdapterFavoritos(ItemFilmeClick click) {
        itemFilmeClick = click;
        filmes = new ArrayList<>();
    }

    @NonNull
    @Override
    public FavoritosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filme, parent, false);
        return new FavoritosHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritosHolder holder, int position) {
        holder.bind(filmes.get(position));
    }

    @Override
    public int getItemCount() {
        return (filmes != null && filmes.size() != 0) ? filmes.size() : 0;
    }

    public void setFilmes(List<FilmesEntity> filmes) {
        this.filmes = filmes;
        notifyDataSetChanged();
    }

    class FavoritosHolder extends RecyclerView.ViewHolder {
        private TextView text_TituloFilme;
        private TextView text_data;
        private TextView text_genero;
        private ImageView imagePoster;
        private FilmesEntity movie;


        public FavoritosHolder(@NonNull View itemView) {
            super(itemView);
            text_TituloFilme = itemView.findViewById(R.id.txt_titulo_filme);
            text_data = itemView.findViewById(R.id.txt_data_filme);
            text_genero = itemView.findViewById(R.id.txt_genero_filme);
            imagePoster = itemView.findViewById(R.id.image_poster);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemFilmeClick != null) {
                        itemFilmeClick.onItemFilmeClicado(movie);
                    }
                }
            });

        }

        public void bind(FilmesEntity filme) {
            movie = filme;
            text_TituloFilme.setText(filme.getTitulo());
            String[] aux = filme.getGenero().split("-");
            aux = aux[0].split(":");
            text_genero.setText(aux[1].trim());
            text_data.setText(filme.getData());
            if (filme.getUrlPoster() == null) {
                imagePoster.setImageResource(R.drawable.no_image);
            } else {
                Picasso.get().load("https://image.tmdb.org/t/p/w500/" + filme.getUrlPoster())
                        .into(imagePoster);
            }
        }
    }

    public interface ItemFilmeClick { // interface para a activity tratar as mudanças de telas
        void onItemFilmeClicado(FilmesEntity filme);
    }
}
