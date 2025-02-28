package com.eduardoschelive.digiobackend.adapter.purchases.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PurchaseResponseDTO(
        @JsonProperty("codigo")
        String productId,
        @JsonProperty("quantidade")
        Integer amount) {
}
