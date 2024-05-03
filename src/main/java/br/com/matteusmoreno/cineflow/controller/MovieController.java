package br.com.matteusmoreno.cineflow.controller;

import br.com.matteusmoreno.cineflow.domain.Movie;
import br.com.matteusmoreno.cineflow.request.CreateMovieRequest;
import br.com.matteusmoreno.cineflow.response.MovieDetailResponse;
import br.com.matteusmoreno.cineflow.service.MovieService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<MovieDetailResponse> add(@RequestBody @Valid CreateMovieRequest request, UriComponentsBuilder uriBuilder) {
        Movie movie = movieService.createMovie(request);
        URI uri = uriBuilder.path("/movies/add/{id}").buildAndExpand(movie.getId()).toUri();

        return ResponseEntity.created(uri).body(new MovieDetailResponse(movie));
    }
}
