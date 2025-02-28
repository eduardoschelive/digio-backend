package com.eduardoschelive.digiobackend.application.port.outbound;

import com.eduardoschelive.digiobackend.domain.Customer;

import java.util.Collection;
import java.util.Optional;

public interface CustomerRepositoryPort {

    Collection<Customer> getCustomers();

    Optional<Customer> getCustomerByDocument(String document);

}
