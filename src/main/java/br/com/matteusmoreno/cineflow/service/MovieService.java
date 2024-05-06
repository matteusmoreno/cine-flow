package br.com.matteusmoreno.cineflow.service;

import br.com.matteusmoreno.cineflow.domain.Movie;
import br.com.matteusmoreno.cineflow.exception.InvalidMovieException;
import br.com.matteusmoreno.cineflow.exception.MovieAlreadyExistsException;
import br.com.matteusmoreno.cineflow.repository.MovieRepository;
import br.com.matteusmoreno.cineflow.request.CreateMovieRequest;
import br.com.matteusmoreno.cineflow.utils.AppUtils;
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

        Movie movie = appUtils.setMovieAttributes(request.apiKey(), request.title());

        // Check if the movie already exists in the database
        if (movieRepository.existsByTitleIgnoreCase(movie.getTitle())) {
            throw new MovieAlreadyExistsException("The movie has already been added previously.");
        }

        // Check if the movie details are null or empty
        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            throw new InvalidMovieException("Incorrect movie name or movie not found.");
        }

        movie.setAddedAt(LocalDateTime.now());
        movie.setActive(true);

        movieRepository.save(movie);

        return movie;
    }
}
