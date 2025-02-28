package com.eduardoschelive.digiobackend.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

public class Customer {

    private String name;
    private String document;
    private Collection<Purchase> purchases;

    public Customer(
            String name,
            String document
    ) {
        this.name = name;
        this.document = document;
    }

    public Customer(String name, String document, Collection<Purchase> purchases) {
        this.name = name;
        this.document = document;
        this.purchases = purchases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Collection<Purchase> getPurchases() {

        return purchases;
    }

    public void setPurchases(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }

    public int getPurchaseCount() {
        return purchases.size();
    }

    public BigDecimal getAveragePurchaseValue() {
        if (purchases.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return purchases.stream()
                .map(Purchase::getTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(purchases.size()), RoundingMode.HALF_UP);
    }

}
