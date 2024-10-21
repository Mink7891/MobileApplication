package ru.mirea.logunovao.movieproject.domain.repository;


import ru.mirea.logunovao.movieproject.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);
    public Movie getMovie();
}