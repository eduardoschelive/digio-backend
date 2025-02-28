package com.eduardoschelive.digiobackend.adapter.purchases.outbound;

import com.eduardoschelive.digiobackend.adapter.customer.outbound.CustomerResponse;
import com.eduardoschelive.digiobackend.application.port.outbound.ProductRepositoryPort;
import com.eduardoschelive.digiobackend.application.port.outbound.PurchaseRepositoryPort;
import com.eduardoschelive.digiobackend.domain.Customer;
import com.eduardoschelive.digiobackend.domain.Purchase;
import com.eduardoschelive.digiobackend.infrastructure.annotations.Adapter;
import com.eduardoschelive.digiobackend.infrastructure.clients.DigioClient;
import com.eduardoschelive.digiobackend.infrastructure.config.DigioApiConfig;
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

    @Override
    public Collection<Purchase> getPurchases() {
        var clientsResponse = digioClient.getRestClient()
                .get()
                .uri(digioApiConfig.customerEndpoint)
                .retrieve()
                .body(new ParameterizedTypeReference<List<CustomerResponse>>() {
                });

        if (clientsResponse == null) {
            return List.of();
        }

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
}
