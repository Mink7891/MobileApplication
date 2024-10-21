package ru.mirea.logunovao.movieproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ru.mirea.logunovao.movieproject.data.repository.MovieRepositoryImpl;
import ru.mirea.logunovao.movieproject.domain.GetFavoriteFilmUseCase;


import ru.mirea.logunovao.movieproject.domain.SaveMovieToFavoriteUseCase;
import ru.mirea.logunovao.movieproject.domain.models.Movie;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText text = findViewById(R.id.editTextMovie);
        TextView textView = findViewById(R.id.textViewMovie);
        MovieRepositoryImpl movieRepository = new MovieRepositoryImpl(this);
        findViewById(R.id.buttonSaveMovie).setOnClickListener(new
                                                                      View.OnClickListener() {
                                                                          @Override
                                                                          public void onClick(View view) {
                                                                              Boolean result = new
                                                                                      SaveMovieToFavoriteUseCase(movieRepository).execute(new Movie(2,
                                                                                      text.getText().toString()));
                                                                              textView.setText(String.format("Save result %s", result));
                                                                          }
                                                                      });
        findViewById(R.id.buttonGetMovie).setOnClickListener(new
                                                                     View.OnClickListener() {
                                                                         @Override
                                                                         public void onClick(View view) {
                                                                             Movie moview = new GetFavoriteFilmUseCase(movieRepository).execute();
                                                                             textView.setText(String.format(moview.getName()));
                                                                         }
                                                                     });
    }
}