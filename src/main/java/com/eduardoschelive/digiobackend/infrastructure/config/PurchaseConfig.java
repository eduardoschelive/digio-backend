package com.eduardoschelive.digiobackend.infrastructure.config;

import com.eduardoschelive.digiobackend.application.port.outbound.PurchaseRepositoryPort;
import com.eduardoschelive.digiobackend.application.service.PurchaseService;
import com.eduardoschelive.digiobackend.application.usecases.PurchaseUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PurchaseConfig {

    private final PurchaseRepositoryPort purchaseRepositoryPort;

    @Bean
    public PurchaseUseCases purchaseService() {
        return new PurchaseService(purchaseRepositoryPort);
    }

}
