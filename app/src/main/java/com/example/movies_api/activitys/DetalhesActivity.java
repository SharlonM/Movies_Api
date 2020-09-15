package com.example.movies_api.activitys;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.movies_api.R;
import com.example.movies_api.database.FilmeViewModel;
import com.example.movies_api.database.FilmesEntity;
import com.example.movies_api.model.Filme;
import com.example.movies_api.model.Series;
import com.squareup.picasso.Picasso;

public class DetalhesActivity extends AppCompatActivity {

    public static final String extra_filme = "EXTRA_FILME";
    public static final String extra_serie = "EXTRA_SERIE";
    public boolean favorito;
    private Toolbar toolbar;
    private ImageView imagePoster;
    private TextView txtTituloOriginal, txtData, txtOverview,
            txtAdulto, txtGenero, txtLanguage, txtPopularidade;
    private Filme filme;
    private Series serie;
    private FilmeViewModel filmeViewModel;
    String generos;
    private boolean choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        toolbar = findViewById(R.id.toolbar_fav);
        setSupportActionBar(toolbar);
        inicializarComponentes();

        //recuperar dados da activity anterior
        choice = getIntent().getBooleanExtra("choice", true);
        generos = getIntent().getStringExtra("generos");
        favorito = getIntent().getBooleanExtra("favorito", false);
        txtGenero.setText(generos);

        if (choice) {
            inicializarFilmes();
        } else {
            inicializarSeries();
        }

    }

    void inicializarFilmes() {

        // recebe o objeto da activity anterior e seta seus valores para exibição )

        filme = (Filme) getIntent().getSerializableExtra(extra_filme);

        toolbar.setTitle(filme.getTitulo());
        if (filme.getUrlPosterSecundario() == null) {
            imagePoster.setImageResource(R.drawable.no_image);
        } else {
            Picasso.get().load("https://image.tmdb.org/t/p/original/" + filme.getUrlPosterSecundario())
                    .into(imagePoster);
        }


        SpannableStringBuilder str = new SpannableStringBuilder(txtTituloOriginal.getText() + "    " + filme.getTituloOriginal());
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 16, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        txtTituloOriginal.setText(str);
        txtOverview.setText(txtOverview.getText() + "    " + filme.getOverview());
        txtLanguage.setText("Idioma inicial:    " + filme.getLanguage());
        txtData.setText("Data de lançamento:    " + filme.getData());
        txtAdulto.setText(filme.isAdulto() ? "Classificação:    Adulta" : "Classificação:    Livre");
        txtPopularidade.setText("Popularidade:    " + filme.getPopularidade());
    }

    void inicializarSeries() {

        // recebe o objeto da activity anterior e seta seus valores para exibição )

        serie = (Series) getIntent().getSerializableExtra(extra_serie);

        toolbar.setTitle(serie.getNome());
        if (serie.getUrlPosterOriginal() == null) {
            imagePoster.setImageResource(R.drawable.no_image);
        } else {
            Picasso.get().load("https://image.tmdb.org/t/p/original/" + serie.getUrlPosterOriginal())
                    .into(imagePoster);
        }


        SpannableStringBuilder str = new SpannableStringBuilder(txtTituloOriginal.getText() + "    " + serie.getNome_original());
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 16, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        txtTituloOriginal.setText(str);
        txtOverview.setText(txtOverview.getText() + "    " + serie.getOverview());
        txtLanguage.setText("Idioma inicial:    " + serie.getLanguage());
        txtData.setText("Data de lançamento:    " + serie.getDate());
        txtAdulto.setText("Classificação:    Livre");
        txtPopularidade.setText("Popularidade:    " + serie.getPopularidade());
    }

    void inicializarComponentes() {
        imagePoster = findViewById(R.id.img_poster);
        txtAdulto = findViewById(R.id.txt_adult);
        txtData = findViewById(R.id.txt_data);
        txtGenero = findViewById(R.id.txt_genero);
        txtLanguage = findViewById(R.id.txt_idioma);
        txtOverview = findViewById(R.id.txt_sinopse);
        txtTituloOriginal = findViewById(R.id.txt_tituloOriginal);
        txtPopularidade = findViewById(R.id.txt_popularidade);
    }

    void adcionarAosFavoritos() {

        FilmesEntity filmesEntity = new FilmesEntity(
                filme.getTituloOriginal(),
                filme.getUrlPoster(),
                filme.getTitulo(),
                generos,
                filme.getData(),
                filme.isAdulto(),
                filme.getOverview(),
                filme.getLanguage(),
                filme.getPopularidade(),
                filme.getUrlPosterSecundario()
        );
        filmeViewModel = new ViewModelProvider(DetalhesActivity.this).get(FilmeViewModel.class);
        filmeViewModel.insert(filmesEntity);
        Toast.makeText(this, "Adcionado aos favoritos", Toast.LENGTH_LONG).show();
    }

    void removerFavorito() {

        FilmesEntity filmesEntity = new FilmesEntity(
                filme.getTituloOriginal(),
                filme.getUrlPoster(),
                filme.getTitulo(),
                generos,
                filme.getData(),
                filme.isAdulto(),
                filme.getOverview(),
                filme.getLanguage(),
                filme.getPopularidade(),
                filme.getUrlPosterSecundario()
        );
        filmesEntity.setId(filme.getId());

        filmeViewModel = new ViewModelProvider(this).get(FilmeViewModel.class);
        filmeViewModel.delete(filmesEntity);
        Toast.makeText(this, "Removido dos favoritos", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (choice) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_favorito, menu);
            if (favorito) {
                menu.getItem(0).setIcon(R.drawable.ic_favorite);
            }
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // onclick para favoritar e desfavoritar um filme

        switch (item.getItemId()) {
            case R.id.item_favoritar:
                if (favorito) {
                    favorito = false;
                    removerFavorito();
                    item.setIcon(R.drawable.ic_nofavorite);
                } else {
                    favorito = true;
                    adcionarAosFavoritos();
                    item.setIcon(R.drawable.ic_favorite);
                }
                break;
            default:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}