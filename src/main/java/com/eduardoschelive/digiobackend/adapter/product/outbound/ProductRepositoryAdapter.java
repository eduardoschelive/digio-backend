package com.eduardoschelive.digiobackend.adapter.product.outbound;

import com.eduardoschelive.digiobackend.application.port.outbound.ProductRepositoryPort;
import com.eduardoschelive.digiobackend.domain.Product;
import com.eduardoschelive.digiobackend.infrastructure.annotations.Adapter;
import com.eduardoschelive.digiobackend.infrastructure.clients.DigioClient;
import com.eduardoschelive.digiobackend.infrastructure.config.DigioApiConfig;
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

    @Override
    public Collection<Product> getProducts() {
        var productResponses = digioClient.getRestClient()
                .get()
                .uri(digioApiConfig.productsEndpoint)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductResponseDTO>>() {
                });

        if (productResponses == null) { // TODO: exception
            return List.of();
        }

        return productResponses.stream()
                .map(ProductResponseDTO::toProduct)
                .toList();
    }

    @Override
    public Map<Integer, Product> getProductsGroupedById() {
        return getProducts().stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
    }

}
