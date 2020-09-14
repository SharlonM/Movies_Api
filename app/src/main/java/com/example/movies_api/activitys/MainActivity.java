package com.example.movies_api.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_api.R;
import com.example.movies_api.http.Api_Services;
import com.example.movies_api.http.Mapper_adapter;
import com.example.movies_api.http.Mochi_Result;
import com.example.movies_api.http.filmes.Filmes_Generos;
import com.example.movies_api.model.Filme;
import com.example.movies_api.model.Generos;
import com.example.movies_api.model.Series;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements listaAdapter.ItemFilmeClick {

    private RecyclerView recyclerView;
    private listaAdapter listaAdapter;
    private BottomNavigationView bottomNavigationView;
    private int countPage = 1;
    private int maxPage = 10;
    private ProgressBar progressBar;
    private static List<Generos> generos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configAdapter();
        progressBar = findViewById(R.id.progress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigator();

        obterFilmes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_barra, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_search:
                // configurar pesquisa
                break;
            case R.id.item_top:
                // voltar scroll para o topo
                recyclerView.smoothScrollToPosition(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bottomNavigator() {

        // barra de navegação inferior, inicialização e ações de click para troca de activitys.

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.item_populares);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.item_cartaz:
                        startActivity(new Intent(getApplicationContext(), CartazActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.item_favoritos:
                        startActivity(new Intent(getApplicationContext(), FavoritosActivity.class));
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

    private void obterFilmes() {

        // requisitar get dos filmes, retornando para classe para filtrar somente o objeto result do json
        // recebendo as respostas e passando pela parse para converter em objeto

        Api_Services.getInstance().getFilmesPopulares("f321a808e68611f41312aa8408531476", "pt-BR", countPage++)
                .enqueue(new Callback<Mochi_Result>() {
                    @Override
                    public void onResponse(Call<Mochi_Result> call, Response<Mochi_Result> response) {
                        if (response.isSuccessful()) {
                            listaAdapter.setFilmes(Mapper_adapter
                                    .responseParse(response.body().getResults()));
                            maxPage = response.body().getTotal_pages();
                        } else {
                            showError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Mochi_Result> call, Throwable t) {
                        showError();
                    }
                });

        // requisitando get dos generos para enviar ao adapter( aparecer no grid ) e para os detalhes (click)

        Api_Services.getInstance().getGen("f321a808e68611f41312aa8408531476", "pt-BR")
                .enqueue(new Callback<Filmes_Generos>() {
                    @Override
                    public void onResponse(Call<Filmes_Generos> call, Response<Filmes_Generos> response) {
                        if (response.isSuccessful()) {
                            generos = response.body().getGeneros();
                            listaAdapter.setGeneros(generos);
                        } else {
                            Toast.makeText(MainActivity.this, "Erro ao obter generos", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Filmes_Generos> call, Throwable t) {

                    }
                });
    }

    private void showError() {
        Toast.makeText(this, "Erro ao obter filmes", Toast.LENGTH_LONG).show();
    }

    private void configAdapter() {

        // configuração do adapter ( receber cada objeto para colocar no grid )
        // verificação realtime do scroll pra saber quando estiver no final para carregar mais(infinity loading)

        recyclerView = findViewById(R.id.view_filmes);
        listaAdapter = new listaAdapter(this, true);
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
                    obterFilmes();
                    progressBar.setVisibility(View.GONE);
                }
            }, 1000);
        }

    }

    @Override
    public void onItemFilmeClicado(Filme filme, List<Generos> gene, Series serie) {

        // verifica o objeto clicado para abrir nova entidade passando o objeto clicado para exibir detalhes
        int[] genFilme = filme.getGenero();
        String aux = "Gêneros:    ";

        for (int i = 0; i < generos.size(); i++) {
            for (int j = 0; j < genFilme.length; j++) {
                if (generos.get(i).getId() == genFilme[j]) {
                    aux += generos.get(i).getGenero() + "-";
                }
            }
        }

        Intent intent = new Intent(this, DetalhesActivity.class);
        intent.putExtra(DetalhesActivity.extra_filme, filme);
        intent.putExtra("generos", aux);
        startActivity(intent);

    }
}