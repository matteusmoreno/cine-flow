package br.com.matteusmoreno.cineflow.repository;

import br.com.matteusmoreno.cineflow.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
}
