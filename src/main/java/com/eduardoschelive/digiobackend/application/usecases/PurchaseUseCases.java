package com.eduardoschelive.digiobackend.application.usecases;

import com.eduardoschelive.digiobackend.application.exception.NoPurchasesFoundOnYear;
import com.eduardoschelive.digiobackend.domain.Purchase;

import java.util.Collection;

public interface PurchaseUseCases {

    Collection<Purchase> getPurchasesSortedByTotalValue();

    Purchase getHighestPurchasesByYear(Integer year) throws NoPurchasesFoundOnYear;

}
