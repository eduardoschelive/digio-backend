package com.eduardoschelive.digiobackend.adapter.customer.outbound;

import com.eduardoschelive.digiobackend.application.port.outbound.CustomerRepositoryPort;
import com.eduardoschelive.digiobackend.application.port.outbound.ProductRepositoryPort;
import com.eduardoschelive.digiobackend.domain.Customer;
import com.eduardoschelive.digiobackend.domain.Purchase;
import com.eduardoschelive.digiobackend.infrastructure.annotations.Adapter;
import com.eduardoschelive.digiobackend.infrastructure.clients.DigioClient;
import com.eduardoschelive.digiobackend.infrastructure.config.DigioApiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Adapter
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {
    private final DigioClient digioClient;
    private final DigioApiConfig digioApiConfig;
    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Collection<Customer> getCustomers() {
        var customerResponses = digioClient.getRestClient().get().uri(digioApiConfig.customerEndpoint).retrieve().body(new ParameterizedTypeReference<List<CustomerResponse>>() {
        });

        if (customerResponses == null) {
            return List.of();
        }

        var productsMap = productRepositoryPort.getProductsGroupedById();

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
    public Optional<Customer> getCustomerByDocument(String document) {
        return getCustomers().stream().filter(customer -> customer.getDocument().equals(document)).findFirst();
    }

}
