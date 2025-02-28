package com.eduardoschelive.digiobackend.application.port.outbound;

import com.eduardoschelive.digiobackend.domain.Purchase;

import java.util.Collection;

public interface PurchaseRepositoryPort {

    Collection<Purchase> getPurchases();

}
