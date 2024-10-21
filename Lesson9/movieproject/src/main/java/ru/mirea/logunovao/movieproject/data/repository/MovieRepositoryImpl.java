package ru.mirea.logunovao.movieproject.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import ru.mirea.logunovao.movieproject.domain.models.Movie;
import ru.mirea.logunovao.movieproject.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {

    private static final String PREFERENCES_FILE = "ru.mirea.logunovao.movieproject.PREFS";
    private static final String FAVORITE_MOVIE_ID = "favorite_movie_id";
    private static final String FAVORITE_MOVIE_NAME = "favorite_movie_name";
    private SharedPreferences sharedPreferences;

    // Конструктор для инициализации SharedPreferences
    public MovieRepositoryImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    // Сохранение фильма в SharedPreferences
    @Override
    public boolean saveMovie(Movie movie) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(FAVORITE_MOVIE_ID, movie.getId());
        editor.putString(FAVORITE_MOVIE_NAME, movie.getName());
        return editor.commit();  // Возвращает true, если сохранение прошло успешно
    }

    // Получение фильма из SharedPreferences
    @Override
    public Movie getMovie() {
        int movieId = sharedPreferences.getInt(FAVORITE_MOVIE_ID, -1);
        String movieName = sharedPreferences.getString(FAVORITE_MOVIE_NAME, null);

        if (movieId == -1 || movieName == null) {
            return null;  // Если фильм не найден
        }

        return new Movie(movieId, movieName);
    }
}
