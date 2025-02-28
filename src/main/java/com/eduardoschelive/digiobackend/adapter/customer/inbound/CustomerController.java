package com.eduardoschelive.digiobackend.adapter.customer.inbound;

import com.eduardoschelive.digiobackend.adapter.product.inbout.ProductDTO;
import com.eduardoschelive.digiobackend.application.port.inbound.CustomerEndpointPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class CustomerController {

    private final CustomerEndpointPort customerEndpointPort;

    @GetMapping("/clientes-fieis")
    public ResponseEntity<Map<Integer, CustomerDTO>> getTopCustomers() {
        var customers = customerEndpointPort.getTopCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/recomendacao/{document}/tipo")
    public ResponseEntity<ProductDTO> getRecommendationByCustomer(@PathVariable String document) {
        var product = customerEndpointPort.getRecommendationByCustomer(document);
        return ResponseEntity.ok(product);
    }

}
