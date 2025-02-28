package com.eduardoschelive.digiobackend.adapter.customer.inbound;

import com.eduardoschelive.digiobackend.domain.Customer;

public record CustomerDTO(
        String name,
        String document
) {

    public static CustomerDTO fromCustomer(Customer customer) {
        return new CustomerDTO(customer.getName(), customer.getDocument());
    }

}
