package br.com.matteusmoreno.cineflow.repository;

import br.com.matteusmoreno.cineflow.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
