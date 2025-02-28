package com.eduardoschelive.digiobackend.application.port.inbound;

import com.eduardoschelive.digiobackend.adapter.customer.inbound.CustomerDTO;
import com.eduardoschelive.digiobackend.adapter.product.inbout.ProductDTO;

import java.util.Map;

public interface CustomerEndpointPort {

    /**
     * @return a map containing the top customers and their respective scores
     */
    Map<Integer, CustomerDTO> getTopCustomers();

    /**
     * @param customerDocument the document of the customer for which a recommendation is to be retrieved
     * @return the product recommended for the specified customer
     */
    ProductDTO getRecommendationByCustomer(String customerDocument);

}
