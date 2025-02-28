package com.eduardoschelive.digiobackend.adapter.customer.inbound;

import com.eduardoschelive.digiobackend.adapter.product.inbout.ProductDTO;
import com.eduardoschelive.digiobackend.application.port.inbound.CustomerEndpointPort;
import com.eduardoschelive.digiobackend.application.usecases.CustomerUseCases;
import com.eduardoschelive.digiobackend.infrastructure.annotations.Adapter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Adapter
@RequiredArgsConstructor
public class CustomerEndpointAdapter implements CustomerEndpointPort {

    private final CustomerUseCases customerUseCases;

    @Override
    public Map<Integer, CustomerDTO> getTopCustomers() {
        return customerUseCases.getTopCustomers().entrySet().stream()
                .collect(
                        HashMap::new,
                        (map, entry) -> map.put(entry.getKey(), CustomerDTO.fromCustomer(entry.getValue())),
                        Map::putAll
                );
    }

    @Override
    public ProductDTO getRecommendationByCustomer(String customerDocument) {
        return ProductDTO.fromProduct(customerUseCases.getRecommendationByCustomer(customerDocument));
    }
}