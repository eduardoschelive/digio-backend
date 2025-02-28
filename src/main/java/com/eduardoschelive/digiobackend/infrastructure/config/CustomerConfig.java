package com.eduardoschelive.digiobackend.infrastructure.config;


import com.eduardoschelive.digiobackend.application.port.outbound.CustomerRepositoryPort;
import com.eduardoschelive.digiobackend.application.port.outbound.ProductRepositoryPort;
import com.eduardoschelive.digiobackend.application.service.CustomerService;
import com.eduardoschelive.digiobackend.application.usecases.CustomerUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CustomerConfig {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;

    @Value("${customer.top.percentile}")
    private Integer topPercentile;

    @Bean
    public CustomerUseCases customerService() {
        return new CustomerService(customerRepositoryPort, productRepositoryPort, topPercentile);
    }

}
