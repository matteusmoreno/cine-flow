package br.com.matteusmoreno.cineflow.client;

import br.com.matteusmoreno.cineflow.domain.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "omdbClient", url = "http://www.omdbapi.com")
public interface OmdbClient {

    @GetMapping("/")
    Movie getMovie(@RequestParam("apikey") String apiKey, @RequestParam("t") String title);
}
