package com.example.movies_api.activitys;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.movies_api.R;
import com.example.movies_api.model.Filme;
import com.squareup.picasso.Picasso;

public class DetalhesActivity extends AppCompatActivity {

    public static final String extra_filme = "EXTRA_FILME";
    public boolean favorito;
    private ImageView imagePoster;
    private TextView txtTituloOriginal, txtData, txtOverview,
            txtAdulto, txtGenero, txtLanguage, txtPopularidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Toolbar toolbar = findViewById(R.id.toolbar_fav);
        setSupportActionBar(toolbar);
        inicializarComponentes();

        Filme filme = (Filme) getIntent().getSerializableExtra(extra_filme);
        String generos = getIntent().getStringExtra("generos");
        toolbar.setTitle(filme.getTitulo());
        Picasso.get().load("https://image.tmdb.org/t/p/original/" + filme.getUrlPosterSecundario())
                .into(imagePoster);


        SpannableStringBuilder str = new SpannableStringBuilder(txtTituloOriginal.getText() + "    " + filme.getTituloOriginal());
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 16, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        txtTituloOriginal.setText(str);
        txtOverview.setText(txtOverview.getText() + "    " + filme.getOverview());
        txtLanguage.setText("Idioma inicial:    " + filme.getLanguage());
        txtData.setText("Data de lançamento:    " + filme.getData());
        txtAdulto.setText(filme.isAdulto() ? "Classificação:    Adulta" : "Classificação:    Livre");
        txtPopularidade.setText("Popularidade:    " + filme.getPopularidade());
        txtGenero.setText(generos);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_favorito, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_favoritar:
                if (favorito) {
                    item.setIcon(R.drawable.ic_nofavorite);
                    favorito = false;
                    System.out.println("no fav");
                } else {
                    item.setIcon(R.drawable.ic_favorite);
                    favorito = true;
                    System.out.println("fav");
                }
                break;
            default:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}