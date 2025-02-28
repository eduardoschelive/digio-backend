package com.eduardoschelive.digiobackend.adapter.purchases.inbound;

import com.eduardoschelive.digiobackend.application.exception.NoPurchasesFoundOnYear;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class PurchasesController {

    private final PurchaseEndpointAdapter purchaseEndpointAdapter;

    @GetMapping("/compras")
    public ResponseEntity<Collection<PurchaseDTO>> getPurchases() {
        var purchases = purchaseEndpointAdapter.getPurchasesSortedByTotalValue();
        return ResponseEntity.ok(purchases);
    }

    @GetMapping("/maior-compra/{year}")
    public ResponseEntity<PurchaseDTO> getHighestPurchasesByYear(@PathVariable Integer year) {
        try {
            var highestPurchase = purchaseEndpointAdapter.getHighestPurchasesByYear(year);
            return ResponseEntity.ok(highestPurchase);
        } catch (NoPurchasesFoundOnYear e) {
            // TODO: use controller advice to handle this exception
            return ResponseEntity.notFound().build();
        }
    }

}
