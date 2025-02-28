package com.eduardoschelive.digiobackend.adapter.purchases.inbound;

import com.eduardoschelive.digiobackend.adapter.customer.inbound.CustomerDTO;
import com.eduardoschelive.digiobackend.adapter.product.inbout.ProductDTO;
import com.eduardoschelive.digiobackend.domain.Purchase;

import java.math.BigDecimal;

public record PurchaseDTO(
        CustomerDTO customer,
        ProductDTO product,
        Integer amount,
        BigDecimal totalValue
) {

    public static PurchaseDTO fromPurchase(Purchase purchase) {
        return new PurchaseDTO(CustomerDTO.fromCustomer(purchase.getCustomer()), ProductDTO.fromProduct(purchase.getProduct()), purchase.getAmount(), purchase.getTotalValue());
    }

}
