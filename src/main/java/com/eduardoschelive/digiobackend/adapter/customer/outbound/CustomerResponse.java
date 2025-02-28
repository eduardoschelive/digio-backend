package com.eduardoschelive.digiobackend.adapter.customer.outbound;

import com.eduardoschelive.digiobackend.adapter.purchases.outbound.PurchaseResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public record CustomerResponse(
        @JsonProperty("nome")
        String name,
        @JsonProperty("cpf")
        String document,
        @JsonProperty("compras")
        Collection<PurchaseResponseDTO> purchases
) {
}
