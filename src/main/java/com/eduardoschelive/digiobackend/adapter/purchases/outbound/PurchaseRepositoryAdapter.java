package com.eduardoschelive.digiobackend.adapter.purchases.outbound;

import com.eduardoschelive.digiobackend.adapter.customer.outbound.CustomerResponse;
import com.eduardoschelive.digiobackend.application.port.outbound.CustomerRepositoryPort;
import com.eduardoschelive.digiobackend.application.port.outbound.ProductRepositoryPort;
import com.eduardoschelive.digiobackend.application.port.outbound.PurchaseRepositoryPort;
import com.eduardoschelive.digiobackend.domain.Customer;
import com.eduardoschelive.digiobackend.domain.Purchase;
import com.eduardoschelive.digiobackend.infrastructure.annotations.Adapter;
import com.eduardoschelive.digiobackend.infrastructure.clients.DigioClient;
import com.eduardoschelive.digiobackend.infrastructure.config.DigioApiConfig;
import com.eduardoschelive.digiobackend.infrastructure.exception.DigioAPIException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Collection;
import java.util.List;

@Adapter
@RequiredArgsConstructor
public class PurchaseRepositoryAdapter implements PurchaseRepositoryPort {

    private final DigioClient digioClient;
    private final DigioApiConfig digioApiConfig;
    private final ProductRepositoryPort productRepositoryPort;

    private List<CustomerResponse> fetchClientResponses() {
        var clientsResponse = digioClient.getRestClient()
                .get()
                .uri(digioApiConfig.customerEndpoint)
                .retrieve()
                .body(new ParameterizedTypeReference<List<CustomerResponse>>() {
                });

        return clientsResponse == null ? List.of() : clientsResponse;
    }

    private Collection<Purchase> processClientResponses(List<CustomerResponse> clientsResponse) {
        var productsMap = productRepositoryPort.getProductsGroupedById();

        return clientsResponse.stream()
                .flatMap(customerResponse -> {
                    var customer = new Customer(customerResponse.document(), customerResponse.name());
                    var purchases = customerResponse.purchases().stream()
                            .map(purchaseResponseDTO -> {
                                var productId = Integer.parseInt(purchaseResponseDTO.productId());
                                var product = productsMap.get(productId);
                                return new Purchase(purchaseResponseDTO.amount(), product, customer);
                            }).toList();
                    customer.setPurchases(purchases);
                    return purchases.stream();
                }).toList();
    }

    @Override
    @CircuitBreaker(name = "purchases-circuit-breaker")
    public Collection<Purchase> getPurchases() {
        try {
            var clientsResponse = fetchClientResponses();
            return processClientResponses(clientsResponse);
        } catch (Exception e) {
            throw new DigioAPIException(digioApiConfig.customerEndpoint);
        }
    }
}
