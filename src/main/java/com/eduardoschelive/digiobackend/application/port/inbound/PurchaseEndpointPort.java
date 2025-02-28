package com.eduardoschelive.digiobackend.application.port.inbound;

import com.eduardoschelive.digiobackend.adapter.purchases.inbound.PurchaseDTO;

import java.util.Collection;

public interface PurchaseEndpointPort {

    /**
     * @return a collection of purchases sorted by total value
     */
    Collection<PurchaseDTO> getPurchasesSortedByTotalValue();

    /**
     * @param year the year for which the highest purchase is to be retrieved
     * @return the highest purchase made in the specified year
     */
    PurchaseDTO getHighestPurchasesByYear(Integer year);

}
