package br.com.matteusmoreno.cineflow.repository;

import br.com.matteusmoreno.cineflow.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
