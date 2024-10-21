package ru.mirea.logunovao.movieproject.domain;

import ru.mirea.logunovao.movieproject.data.repository.MovieRepositoryImpl;
import ru.mirea.logunovao.movieproject.domain.models.Movie;

public class SaveMovieToFavoriteUseCase {
    private MovieRepositoryImpl movieRepository;
    public SaveMovieToFavoriteUseCase(MovieRepositoryImpl movieRepository) {
        this.movieRepository = movieRepository;
    }
    public boolean execute(Movie movie){
        return movieRepository.saveMovie(movie);
    }
}
