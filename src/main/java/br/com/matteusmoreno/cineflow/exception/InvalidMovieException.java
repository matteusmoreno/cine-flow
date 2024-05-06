package br.com.matteusmoreno.cineflow.exception;

public class InvalidMovieException extends RuntimeException{
    public InvalidMovieException(String message) {
        super(message);
    }
}
