package br.com.matteusmoreno.cineflow.controller;

import br.com.matteusmoreno.cineflow.domain.Movie;
import br.com.matteusmoreno.cineflow.request.CreateMovieRequest;
import br.com.matteusmoreno.cineflow.response.MovieDetailResponse;
import br.com.matteusmoreno.cineflow.service.MovieService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

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

    @GetMapping("/detail/{id}")
    public ResponseEntity<MovieDetailResponse> detail(@PathVariable UUID id) {
        Movie movie = movieService.movieDetail(id);

        return ResponseEntity.ok(new MovieDetailResponse(movie));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<MovieDetailResponse>> list(@PageableDefault(size = 10, sort = {"title"}) Pageable pageable) {
        var page = movieService.listAllMovies(pageable);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/disable/{id}")
    @Transactional
    public ResponseEntity<Void> disable(@PathVariable UUID id) {
        movieService.disableMovie(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/enable/{id}")
    @Transactional
    public ResponseEntity<MovieDetailResponse> enable(@PathVariable UUID id) {
        Movie movie = movieService.enableMovie(id);

        return ResponseEntity.ok(new MovieDetailResponse(movie));
    }
}
