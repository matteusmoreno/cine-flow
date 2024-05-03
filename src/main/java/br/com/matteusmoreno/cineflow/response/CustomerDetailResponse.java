package br.com.matteusmoreno.cineflow.response;

import br.com.matteusmoreno.cineflow.domain.Address;
import br.com.matteusmoreno.cineflow.domain.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerDetailResponse(
        UUID id,
        String name,
        LocalDate birthDate,
        Integer age,
        String email,
        Address address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        Boolean active) {

    public CustomerDetailResponse(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getBirthDate(), customer.getAge(),
                customer.getEmail(), customer.getAddress(), customer.getCreatedAt(),
                customer.getUpdatedAt(), customer.getDeletedAt(), customer.getActive());
    }
}
