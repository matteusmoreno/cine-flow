package br.com.matteusmoreno.cineflow.client;

import br.com.matteusmoreno.cineflow.domain.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "omdb", url = "https://www.omdbapi.com")
public interface OmdbClient {

    @GetMapping("?t={title}&apikey=885f0db5")
    Movie getMovie(@PathVariable String title);
}
