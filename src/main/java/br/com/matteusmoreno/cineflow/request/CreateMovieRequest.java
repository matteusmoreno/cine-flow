package br.com.matteusmoreno.cineflow.request;

import jakarta.validation.constraints.NotBlank;

public record CreateMovieRequest(
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Api Key is required")
        String apiKey) {
}
