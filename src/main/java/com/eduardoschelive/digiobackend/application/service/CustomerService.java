package com.eduardoschelive.digiobackend.application.service;

import com.eduardoschelive.digiobackend.application.port.outbound.CustomerRepositoryPort;
import com.eduardoschelive.digiobackend.application.port.outbound.ProductRepositoryPort;
import com.eduardoschelive.digiobackend.application.usecases.CustomerUseCases;
import com.eduardoschelive.digiobackend.domain.Customer;
import com.eduardoschelive.digiobackend.domain.Product;
import com.eduardoschelive.digiobackend.domain.Purchase;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CustomerService implements CustomerUseCases {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;
    private final double percentile;

    public CustomerService(CustomerRepositoryPort customerRepositoryPort, ProductRepositoryPort productRepositoryPort, int percentile) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
        this.percentile = percentile > 0 ? percentile : 75;
    }

    @Override
    public Map<Integer, Customer> getTopCustomers() {
        var customers = customerRepositoryPort.getCustomers();

        var position = new AtomicInteger(1);

        return customers.stream()
                .filter(c -> c.getPurchaseCount() > getPercentile(customers))
                .sorted(Comparator.comparing(Customer::getPurchaseCount).reversed()
                        .thenComparing(Customer::getAveragePurchaseValue).reversed())
                .limit(3)
                .collect(Collectors.toMap(c -> position.getAndIncrement(), c -> c));
    }

    @Override
    public Product getRecommendationByCustomer(String customerDocument) {
        var customer = customerRepositoryPort.getCustomerByDocument(customerDocument)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        var mostPurchasedProductType = customer.getPurchases().stream()
                .collect(Collectors.groupingBy(p -> p.getProduct().getType(), Collectors.summingInt(Purchase::getAmount)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new IllegalArgumentException("No purchases found"));

        return customer.getPurchases().stream()
                .filter(p -> p.getProduct().getType().equals(mostPurchasedProductType))
                .collect(Collectors.groupingBy(p -> p.getProduct().getId(), Collectors.summingInt(Purchase::getAmount)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> productRepositoryPort.getProducts().stream()
                        .filter(p -> p.getId().equals(e.getKey()))
                        .findFirst()
                        .orElseThrow())
                .orElseThrow(() -> new IllegalArgumentException("No purchases found"));
    }

    private int getPercentile(Collection<Customer> customers) {
        var purchaseCounts = customers.stream()
                .map(Customer::getPurchaseCount)
                .sorted()
                .toList();

        int index = (int) Math.ceil(purchaseCounts.size() * (percentile / 100.0)) - 1;
        return purchaseCounts.get(index);
    }

}
