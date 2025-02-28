package com.eduardoschelive.digiobackend.application.port.inbound;

import com.eduardoschelive.digiobackend.adapter.customer.inbound.CustomerDTO;
import com.eduardoschelive.digiobackend.adapter.product.inbout.ProductDTO;

import java.util.Map;

public interface CustomerEndpointPort {

    Map<Integer, CustomerDTO> getTopCustomers();

    ProductDTO getRecommendationByCustomer(String customerDocument);

}
