package com.eduardoschelive.digiobackend.adapter.product.outbound;

import com.eduardoschelive.digiobackend.application.port.outbound.ProductRepositoryPort;
import com.eduardoschelive.digiobackend.domain.Product;
import com.eduardoschelive.digiobackend.infrastructure.annotations.Adapter;
import com.eduardoschelive.digiobackend.infrastructure.clients.DigioClient;
import com.eduardoschelive.digiobackend.infrastructure.config.DigioApiConfig;
import com.eduardoschelive.digiobackend.infrastructure.exception.DigioAPIException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Adapter
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {
    private final DigioClient digioClient;
    private final DigioApiConfig digioApiConfig;

    private List<ProductResponseDTO> fetchProductResponses() {
        return digioClient.getRestClient()
                .get()
                .uri(digioApiConfig.productsEndpoint)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductResponseDTO>>() {
                });
    }

    private Collection<Product> processProductResponses(List<ProductResponseDTO> productResponses) {
        return productResponses == null ? List.of() : productResponses.stream()
                .map(ProductResponseDTO::toProduct)
                .toList();
    }

    @CircuitBreaker(name = "products-circuit-breaker")
    @Override
    public Collection<Product> getProducts() {
        try {
            var productResponses = fetchProductResponses();
            return processProductResponses(productResponses);
        } catch (Exception e) {
            throw new DigioAPIException(digioApiConfig.productsEndpoint);
        }
    }

    @CircuitBreaker(name = "products-circuit-breaker")
    @Override
    public Map<Integer, Product> getProductsGroupedById() {
        return getProducts().stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
    }

}
