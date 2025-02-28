package com.eduardoschelive.digiobackend.domain;

import java.math.BigDecimal;

public class Purchase {

    private Integer amount;
    private Product product;
    private Customer customer;

    public Purchase(Integer amount, Product product, Customer customer) {
        this.amount = amount;
        this.product = product;
        this.customer = customer;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalValue() {
        return product.getPrice().multiply(BigDecimal.valueOf(amount));
    }

    public Integer getYear() {
        return product.getPurchaseYear();
    }

}
