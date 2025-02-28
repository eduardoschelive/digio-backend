package com.eduardoschelive.digiobackend.adapter.purchases.inbound;

import com.eduardoschelive.digiobackend.application.exception.NoPurchasesFoundOnYear;
import com.eduardoschelive.digiobackend.application.port.inbound.PurchaseEndpointPort;
import com.eduardoschelive.digiobackend.application.usecases.PurchaseUseCases;
import com.eduardoschelive.digiobackend.infrastructure.annotations.Adapter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Adapter
@RequiredArgsConstructor
public class PurchaseEndpointAdapter implements PurchaseEndpointPort {

    private final PurchaseUseCases purchaseUseCases;

    @Override
    public Collection<PurchaseDTO> getPurchasesSortedByTotalValue() {
        return purchaseUseCases.getPurchasesSortedByTotalValue().stream()
                .map(PurchaseDTO::fromPurchase)
                .toList();
    }

    @Override
    public PurchaseDTO getHighestPurchasesByYear(Integer year) throws NoPurchasesFoundOnYear {
        return PurchaseDTO.fromPurchase(purchaseUseCases.getHighestPurchasesByYear(year));
    }
}
