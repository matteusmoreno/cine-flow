package br.com.matteusmoreno.cineflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CineFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(CineFlowApplication.class, args);
	}

}
