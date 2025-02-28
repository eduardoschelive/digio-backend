package com.eduardoschelive.digiobackend.domain;

import java.math.BigDecimal;

public class Product {

    private Integer id;
    private String type;
    private BigDecimal price;
    private Integer harvest;
    private Integer purchaseYear;

    public Product(Integer id, String type, BigDecimal price, Integer harvest, Integer purchaseYear) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.harvest = harvest;
        this.purchaseYear = purchaseYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getHarvest() {
        return harvest;
    }

    public void setHarvest(Integer harvest) {
        this.harvest = harvest;
    }

    public Integer getPurchaseYear() {
        return purchaseYear;
    }

    public void setPurchaseYear(Integer purchaseYear) {
        this.purchaseYear = purchaseYear;
    }
}
