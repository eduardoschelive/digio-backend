package com.eduardoschelive.digiobackend.application.usecases;

import com.eduardoschelive.digiobackend.application.exception.CustomerNotFoundException;
import com.eduardoschelive.digiobackend.application.exception.NoPurchasesFoundForCustomerException;
import com.eduardoschelive.digiobackend.domain.Customer;
import com.eduardoschelive.digiobackend.domain.Product;

import java.util.Map;

public interface CustomerUseCases {

    /**
     * @return a map of the top customers, where the key is the customer's ID and the value is the customer itself
     */
    Map<Integer, Customer> getTopCustomers();

    /**
     * @param customerDocument the document of the customer for which a recommendation is to be retrieved
     * @return the product recommended for the specified customer
     * @throws CustomerNotFoundException if the customer is not found
     * @throws NoPurchasesFoundForCustomerException if no purchases are found for the specified customer
     */
    Product getRecommendationByCustomer(String customerDocument) throws CustomerNotFoundException, NoPurchasesFoundForCustomerException;

}
