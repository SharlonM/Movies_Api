package com.example.movies_api.activitys.favoritos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_api.R;
import com.example.movies_api.activitys.CartazActivity;
import com.example.movies_api.activitys.DetalhesActivity;
import com.example.movies_api.activitys.MainActivity;
import com.example.movies_api.activitys.SeriesActivity;
import com.example.movies_api.database.FilmeViewModel;
import com.example.movies_api.database.FilmesEntity;
import com.example.movies_api.model.Filme;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class FavoritosActivity extends AppCompatActivity implements AdapterFavoritos.ItemFilmeClick {

    private BottomNavigationView bottomNavigationView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private FilmeViewModel filmeViewModel;
    private AdapterFavoritos adaptarFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        bottomNavigator();
        progressBar = findViewById(R.id.progress_favoritos);
        Toolbar toolbar = findViewById(R.id.toolbar_favoritos);
        setSupportActionBar(toolbar);
        configAdapter();
        realTimeDB();
    }

    private void realTimeDB() {
        filmeViewModel = new ViewModelProvider(FavoritosActivity.this).get(FilmeViewModel.class);
        filmeViewModel.getAllFilmes().observe(this, new Observer<List<FilmesEntity>>() {
            @Override
            public void onChanged(List<FilmesEntity> filmesEntities) {
                // update RecycleView
                System.out.println(filmesEntities.get(0).getTituloOriginal());
                adaptarFavoritos.setFilmes(filmesEntities);
            }
        });
    }

    private void configAdapter() {
        recyclerView = findViewById(R.id.view_favoritos);
        adaptarFavoritos = new AdapterFavoritos(this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptarFavoritos);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        recyclerView.smoothScrollToPosition(0);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_barra, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void bottomNavigator() {
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.item_favoritos);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.item_cartaz:
                        startActivity(new Intent(getApplicationContext(), CartazActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.item_populares:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.item_series:
                        startActivity(new Intent(getApplicationContext(), SeriesActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    default:
                        return true;
                }
            }
        });

    }

    @Override
    public void onItemFilmeClicado(FilmesEntity filme) {
        // verifica o objeto clicado para abrir nova entidade passando o objeto clicado para exibir detalhes

        Boolean favorito = false;
        int[] i = {1, 2};
        String aux = filme.getGenero();

        Filme filmeModel = new Filme(
                filme.getTituloOriginal(),
                filme.getUrlPoster(),
                filme.getTitulo(),
                i,
                filme.getData(),
                filme.isAdulto(),
                filme.getOverview(),
                filme.getLanguage(),
                filme.getPopularidade(),
                filme.getUrlPosterSecundario()
        );

        Intent intent = new Intent(this, DetalhesActivity.class);
        intent.putExtra(DetalhesActivity.extra_filme, filmeModel);
        intent.putExtra("generos", aux);
        intent.putExtra("favorito", favorito);
        startActivity(intent);
    }
}