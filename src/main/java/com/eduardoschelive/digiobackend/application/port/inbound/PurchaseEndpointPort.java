package com.eduardoschelive.digiobackend.application.port.inbound;

import com.eduardoschelive.digiobackend.adapter.purchases.inbound.PurchaseDTO;

import java.util.Collection;

public interface PurchaseEndpointPort {

    Collection<PurchaseDTO> getPurchasesSortedByTotalValue();

    PurchaseDTO getHighestPurchasesByYear(Integer year);

}
