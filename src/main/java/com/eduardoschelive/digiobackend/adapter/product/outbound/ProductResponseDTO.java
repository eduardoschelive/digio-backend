package com.eduardoschelive.digiobackend.adapter.product.outbound;

import com.eduardoschelive.digiobackend.domain.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProductResponseDTO(
        @JsonProperty("codigo")
        Integer id,
        @JsonProperty("tipo_vinho")
        String wineType,
        @JsonProperty("preco")
        BigDecimal price,
        @JsonProperty("safra")
        Integer harvest,
        @JsonProperty("ano_compra")
        Integer purchaseYear
) {

    public Product toProduct() {
        return new Product(id, wineType, price, harvest, purchaseYear);
    }

}
