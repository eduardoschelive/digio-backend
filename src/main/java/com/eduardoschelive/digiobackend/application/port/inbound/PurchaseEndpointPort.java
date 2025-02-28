package com.eduardoschelive.digiobackend.application.port.inbound;

import com.eduardoschelive.digiobackend.adapter.purchases.inbound.PurchaseDTO;
import com.eduardoschelive.digiobackend.application.exception.NoPurchasesFoundOnYear;

import java.util.Collection;

public interface PurchaseEndpointPort {

    Collection<PurchaseDTO> getPurchasesSortedByTotalValue();

    PurchaseDTO getHighestPurchasesByYear(Integer year) throws NoPurchasesFoundOnYear;

}
