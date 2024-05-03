package br.com.matteusmoreno.cineflow.controller;

import br.com.matteusmoreno.cineflow.domain.Customer;
import br.com.matteusmoreno.cineflow.request.CreateCustomerRequest;
import br.com.matteusmoreno.cineflow.request.UpdateCustomerRequest;
import br.com.matteusmoreno.cineflow.response.CustomerDetailResponse;
import br.com.matteusmoreno.cineflow.service.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<CustomerDetailResponse> create(@RequestBody @Valid CreateCustomerRequest request, UriComponentsBuilder urBuilder) {
        Customer customer = customerService.createCustomer(request);
        URI uri = urBuilder.path("/customers/create/{id}").buildAndExpand(customer.getId()).toUri();

        return ResponseEntity.created(uri).body(new CustomerDetailResponse(customer));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<CustomerDetailResponse> detail(@PathVariable UUID id) {
        Customer customer = customerService.customerDetail(id);

        return ResponseEntity.ok(new CustomerDetailResponse(customer));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CustomerDetailResponse>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = customerService.listAllCustomers(pageable);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<CustomerDetailResponse> update(@RequestBody @Valid UpdateCustomerRequest request) {
        Customer customer = customerService.updateCustomer(request);

        return ResponseEntity.ok(new CustomerDetailResponse(customer));
    }

    @DeleteMapping("/disable/{id}")
    @Transactional
    public ResponseEntity<Void> disable(@PathVariable UUID id) {
        customerService.disableCustomer(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/enable/{id}")
    @Transactional
    public ResponseEntity<CustomerDetailResponse> enable(@PathVariable UUID id) {
        Customer customer = customerService.enableCustomer(id);

        return ResponseEntity.ok(new CustomerDetailResponse(customer));
    }
}
