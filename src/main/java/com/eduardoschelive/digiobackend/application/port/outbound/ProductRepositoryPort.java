package com.eduardoschelive.digiobackend.application.port.outbound;

import com.eduardoschelive.digiobackend.domain.Product;

import java.util.Collection;
import java.util.Map;

public interface ProductRepositoryPort {

    Collection<Product> getProducts();

    Map<Integer, Product> getProductsGroupedById();

}
