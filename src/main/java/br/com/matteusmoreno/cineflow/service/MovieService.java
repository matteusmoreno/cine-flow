package br.com.matteusmoreno.cineflow.service;

import br.com.matteusmoreno.cineflow.client.OmdbClient;
import br.com.matteusmoreno.cineflow.domain.Movie;
import br.com.matteusmoreno.cineflow.repository.MovieRepository;
import br.com.matteusmoreno.cineflow.request.CreateMovieRequest;
import br.com.matteusmoreno.cineflow.utils.AppUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final AppUtils appUtils;

    public MovieService(MovieRepository movieRepository, AppUtils appUtils) {
        this.movieRepository = movieRepository;
        this.appUtils = appUtils;
    }

    public Movie createMovie(CreateMovieRequest request) {
        Movie movie = appUtils.setMovieAttributes(request.title());
        movie.setAddedAt(LocalDateTime.now());
        movie.setActive(true);

        movieRepository.save(movie);

        return movie;
    }
}
