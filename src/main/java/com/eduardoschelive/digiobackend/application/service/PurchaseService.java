package com.eduardoschelive.digiobackend.application.service;

import com.eduardoschelive.digiobackend.application.exception.NoPurchasesFoundOnYear;
import com.eduardoschelive.digiobackend.application.port.outbound.PurchaseRepositoryPort;
import com.eduardoschelive.digiobackend.application.usecases.PurchaseUseCases;
import com.eduardoschelive.digiobackend.domain.Purchase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class PurchaseService implements PurchaseUseCases {

    private final PurchaseRepositoryPort purchaseRepositoryPort;

    public PurchaseService(PurchaseRepositoryPort purchaseRepositoryPort) {
        this.purchaseRepositoryPort = purchaseRepositoryPort;
    }

    @Override
    public Collection<Purchase> getPurchasesSortedByTotalValue() {
        var list = new ArrayList<>(purchaseRepositoryPort.getPurchases());
        list.sort(Comparator.comparing(Purchase::getTotalValue));
        return list;
    }

    @Override
    public Purchase getHighestPurchasesByYear(Integer year) throws NoPurchasesFoundOnYear {
        return purchaseRepositoryPort.getPurchases().stream()
                .filter(purchase -> Objects.equals(purchase.getYear(), year))
                .max(Comparator.comparing(Purchase::getTotalValue))
                .orElseThrow(() -> new NoPurchasesFoundOnYear(year));
    }
}
