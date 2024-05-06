package br.com.matteusmoreno.cineflow.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Movie {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Runtime")
    private String runtime;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Writer")
    private String writer;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Poster")
    private String poster;
    private LocalDateTime addedAt;
    private LocalDateTime deletedAt;
    private Boolean active;

}
