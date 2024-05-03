package br.com.matteusmoreno.cineflow.utils;

import br.com.matteusmoreno.cineflow.client.ViaCepClient;
import br.com.matteusmoreno.cineflow.domain.Address;
import br.com.matteusmoreno.cineflow.repository.AddressRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class AppUtils {

    private final AddressRepository addressRepository;

    private final ViaCepClient viaCepClient;

    public AppUtils(AddressRepository addressRepository, ViaCepClient viaCepClient) {
        this.addressRepository = addressRepository;
        this.viaCepClient = viaCepClient;
    }

    public Integer ageCalculator(LocalDate birtDate) {
        LocalDate currentlyDate = LocalDate.now();

        return Period.between(birtDate, currentlyDate).getYears();
    }

    public Address setAddressAttributes(String cep) {
        return viaCepClient.getAddress(cep);
    }
}
