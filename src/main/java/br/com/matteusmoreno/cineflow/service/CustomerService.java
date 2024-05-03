package br.com.matteusmoreno.cineflow.service;

import br.com.matteusmoreno.cineflow.domain.Customer;
import br.com.matteusmoreno.cineflow.repository.CustomerRepository;
import br.com.matteusmoreno.cineflow.request.CreateCustomerRequest;
import br.com.matteusmoreno.cineflow.request.UpdateCustomerRequest;
import br.com.matteusmoreno.cineflow.response.CustomerDetailResponse;
import br.com.matteusmoreno.cineflow.utils.AppUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AppUtils appUtils;

    public CustomerService(CustomerRepository customerRepository, AppUtils appUtils) {
        this.customerRepository = customerRepository;
        this.appUtils = appUtils;
    }


    public Customer createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setAddress(appUtils.setAddressAttributes(request.cep()));
        BeanUtils.copyProperties(request, customer);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setAge(appUtils.ageCalculator(customer.getBirthDate()));
        customer.setActive(true);
        customerRepository.save(customer);

        return customer;
    }

    public Customer customerDetail(UUID id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public Page<CustomerDetailResponse> listAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).map(CustomerDetailResponse::new);
    }

    public Customer updateCustomer(UpdateCustomerRequest request) {
        Customer customer = customerRepository.findById(request.id()).orElseThrow();

        if (request.name() != null) {
            customer.setName(request.name());
        }
        if (request.birthDate() != null) {
            customer.setBirthDate(request.birthDate());
            customer.setAge(appUtils.ageCalculator(request.birthDate()));
        }
        if (request.email() != null) {
            customer.setEmail(request.email());
        }
        if (request.cep() != null) {
            customer.setAddress(appUtils.setAddressAttributes(request.cep()));
        }

        customer.setUpdatedAt(LocalDateTime.now());
        customerRepository.save(customer);

        return customer;
    }

    public void disableCustomer(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setDeletedAt(LocalDateTime.now());
        customer.setActive(false);

        customerRepository.save(customer);
    }

    public Customer enableCustomer(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setDeletedAt(null);
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setActive(true);
        customerRepository.save(customer);

        return customer;
    }
}
