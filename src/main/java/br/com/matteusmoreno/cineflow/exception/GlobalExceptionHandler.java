package br.com.matteusmoreno.cineflow.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidMovieException(InvalidMovieException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> movieAlreadyExistsException(MovieAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
