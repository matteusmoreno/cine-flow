package br.com.matteusmoreno.cineflow.response;

import br.com.matteusmoreno.cineflow.domain.Movie;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record MovieDetailResponse(
        UUID id,
        String title,
        String released,
        String runtime,
        String genre,
        String director,
        String writer,
        String actors,
        LocalDateTime addedAt,
        LocalDateTime deletedAt,
        Boolean active) {

    public MovieDetailResponse(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getReleased(), movie.getRuntime(), movie.getGenre(),
                movie.getDirector(), movie.getWriter(), movie.getActors(), movie.getAddedAt(),
                movie.getDeletedAt(), movie.getActive());
    }
}
