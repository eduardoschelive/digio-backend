package com.eduardoschelive.digiobackend.application.usecases;

import com.eduardoschelive.digiobackend.application.exception.NoPurchasesFoundOnYear;
import com.eduardoschelive.digiobackend.domain.Purchase;

import java.util.Collection;

public interface PurchaseUseCases {

    /**
     * @return a collection of purchases sorted by total value
     */
    Collection<Purchase> getPurchasesSortedByTotalValue();

    /**
     * Retrieves the highest purchase made in a specific year.
     *
     * @param year the year for which the highest purchase is to be retrieved
     * @return the highest purchase made in the specified year
     * @throws NoPurchasesFoundOnYear if no purchases are found for the specified year
     */
    Purchase getHighestPurchasesByYear(Integer year) throws NoPurchasesFoundOnYear;

}
