package com.eduardoschelive.digiobackend.adapter.customer.outbound;

import com.eduardoschelive.digiobackend.application.port.outbound.CustomerRepositoryPort;
import com.eduardoschelive.digiobackend.application.port.outbound.ProductRepositoryPort;
import com.eduardoschelive.digiobackend.domain.Customer;
import com.eduardoschelive.digiobackend.domain.Product;
import com.eduardoschelive.digiobackend.domain.Purchase;
import com.eduardoschelive.digiobackend.infrastructure.annotations.Adapter;
import com.eduardoschelive.digiobackend.infrastructure.clients.DigioClient;
import com.eduardoschelive.digiobackend.infrastructure.config.DigioApiConfig;
import com.eduardoschelive.digiobackend.infrastructure.exception.DigioAPIException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;

import java.util.*;

@Adapter
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {
    private final DigioClient digioClient;
    private final DigioApiConfig digioApiConfig;
    private final ProductRepositoryPort productRepositoryPort;

    private List<CustomerResponse> getCustomersFromAPI() {
        var endpoint = digioApiConfig.customerEndpoint;
        try {
            var customerResponses = digioClient.getRestClient().get().uri(endpoint).retrieve().body(new ParameterizedTypeReference<List<CustomerResponse>>() {
            });
            return Objects.requireNonNullElseGet(customerResponses, List::of);
        } catch (Exception e) {
            throw new DigioAPIException(endpoint);
        }
    }

    private Collection<Customer> processCustomerResponses(List<CustomerResponse> customerResponses, Map<Integer, Product> productsMap) {
        return customerResponses.stream().map(customerResponse -> {
            var customer = new Customer(customerResponse.name(), customerResponse.document());
            var customerPurchases = customerResponse.purchases().stream().map(purchaseResponseDTO -> {
                var productIdAsInt = Integer.parseInt(purchaseResponseDTO.productId());
                var product = productsMap.get(productIdAsInt);
                return new Purchase(productIdAsInt, product, customer);
            }).toList();
            customer.setPurchases(customerPurchases);
            return customer;
        }).toList();
    }

    @Override
    @CircuitBreaker(name = "customers-circuit-breaker")
    public Collection<Customer> getCustomers() {
        Map<Integer, Product> productsMap = productRepositoryPort.getProductsGroupedById();
        var customerResponses = getCustomersFromAPI();
        return processCustomerResponses(customerResponses, productsMap);
    }

    @Override
    @CircuitBreaker(name = "customers-circuit-breaker")
    public Optional<Customer> getCustomerByDocument(String document) {
        return getCustomers().stream().filter(customer -> customer.getDocument().equals(document)).findFirst();
    }
}
