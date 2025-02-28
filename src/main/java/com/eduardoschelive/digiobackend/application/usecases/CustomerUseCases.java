package com.eduardoschelive.digiobackend.application.usecases;

import com.eduardoschelive.digiobackend.domain.Customer;
import com.eduardoschelive.digiobackend.domain.Product;

import java.util.Map;

public interface CustomerUseCases {

    Map<Integer, Customer> getTopCustomers();

    Product getRecommendationByCustomer(String customerDocument);

}
