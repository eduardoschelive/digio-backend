package com.eduardoschelive.digiobackend.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DigioApiConfig {

    @Value("${digio.api.base-url}")
    public String apiUrl;

    @Value("${digio.api.endpoints.products}")
    public String productsEndpoint;

    @Value("${digio.api.endpoints.customer}")
    public String customerEndpoint;

}
