package ru.mirea.logunovao.movieproject.domain;
import ru.mirea.logunovao.movieproject.data.repository.MovieRepositoryImpl;
import ru.mirea.logunovao.movieproject.domain.models.Movie;

public class GetFavoriteFilmUseCase {
    private MovieRepositoryImpl movieRepository;
    public GetFavoriteFilmUseCase(MovieRepositoryImpl movieRepository) {
        this.movieRepository = movieRepository;
    }
    public Movie execute(){
        return movieRepository.getMovie();
    }
}