package com.example.movies_api.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_api.R;
import com.example.movies_api.activitys.favoritos.FavoritosActivity;
import com.example.movies_api.http.Api_Services;
import com.example.movies_api.http.Mapper_adapter;
import com.example.movies_api.http.filmes.Filmes_Generos;
import com.example.movies_api.http.series.Result_Series;
import com.example.movies_api.model.Filme;
import com.example.movies_api.model.Generos;
import com.example.movies_api.model.Series;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesActivity extends AppCompatActivity implements listaAdapter.ItemFilmeClick {

    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private listaAdapter listaAdapter;
    private int countPage = 1;
    private int maxPage = 10;
    private ProgressBar progressBar;
    private static List<Generos> generos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        configAdapter();
        bottomNavigator();
        progressBar = findViewById(R.id.progress_series);
        Toolbar toolbar = findViewById(R.id.toolbar_series);
        setSupportActionBar(toolbar);
        obterSeries();
    }

    private void bottomNavigator() {
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.item_series);
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

                    case R.id.item_favoritos:
                        startActivity(new Intent(getApplicationContext(), FavoritosActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    default:
                        return true;
                }
            }
        });

    }

    private void configAdapter() {
        recyclerView = findViewById(R.id.view_series);
        listaAdapter = new listaAdapter(this, false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listaAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerVi, int dx, int dy) {
                super.onScrolled(recyclerVi, dx, dy);

                if (!recyclerVi.canScrollVertically(1)) {
                    carregarMais();
                }

            }
        });
    }

    private void carregarMais() {
        if (countPage == maxPage) {
            // acabou as paginas, avisar view
        } else {
            progressBar.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    obterSeries();
                    progressBar.setVisibility(View.GONE);
                }
            }, 1000);
        }
    }

    private void obterSeries() {

        Api_Services.getInstance().getSeries("f321a808e68611f41312aa8408531476", "pt-BR", countPage++)
                .enqueue(new Callback<Result_Series>() {
                    @Override
                    public void onResponse(Call<Result_Series> call, Response<Result_Series> response) {
                        if (response.isSuccessful()) {
                            listaAdapter.setSeries(Mapper_adapter.seriesParse(response.body().getResultSerie()));
                            maxPage = response.body().getTotal_page();
                        } else {
                            //erro
                        }
                    }

                    @Override
                    public void onFailure(Call<Result_Series> call, Throwable t) {
                        //erro
                    }
                });

        Api_Services.getInstance().getGenSeries("f321a808e68611f41312aa8408531476", "pt-BR")
                .enqueue(new Callback<Filmes_Generos>() {
                    @Override
                    public void onResponse(Call<Filmes_Generos> call, Response<Filmes_Generos> response) {
                        if (response.isSuccessful()) {
                            generos = response.body().getGeneros();
                            listaAdapter.setGeneros(generos);
                        } else {
                            // erro
                        }
                    }

                    @Override
                    public void onFailure(Call<Filmes_Generos> call, Throwable t) {

                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_barra, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        recyclerView.smoothScrollToPosition(0);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemFilmeClicado(Filme filme, List<Generos> gen, Series serie) {

        int[] genSerie = serie.getGeneros();
        String aux = "Gênero:    ";

        for (int i = 0; i < generos.size(); i++) {
            for (int j = 0; j < genSerie.length; j++) {
                if (generos.get(i).getId() == genSerie[j]) {
                    aux += generos.get(i).getGenero() + " - ";
                }
            }
        }

        Intent intent = new Intent(this, DetalhesActivity.class);
        intent.putExtra(DetalhesActivity.extra_serie, serie);
        intent.putExtra("generos", aux);
        intent.putExtra("choice", false);
        startActivity(intent);

    }
}