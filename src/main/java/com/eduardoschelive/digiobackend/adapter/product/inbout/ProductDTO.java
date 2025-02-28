package com.eduardoschelive.digiobackend.adapter.product.inbout;

import java.math.BigDecimal;

public record ProductDTO(
        Integer id,
        String wineType,
        BigDecimal price,
        Integer harvest,
        Integer purchaseYear
) {

    public static ProductDTO fromProduct(com.eduardoschelive.digiobackend.domain.Product product) {
        return new ProductDTO(product.getId(), product.getType(), product.getPrice(), product.getHarvest(), product.getPurchaseYear());
    }

}
