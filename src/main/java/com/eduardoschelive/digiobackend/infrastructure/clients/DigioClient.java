package com.eduardoschelive.digiobackend.infrastructure.clients;


import com.eduardoschelive.digiobackend.infrastructure.config.DigioApiConfig;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Getter
public class DigioClient {
    private final RestClient restClient;

    public DigioClient(
            DigioApiConfig digioApiConfig
    ) {
        this.restClient = RestClient.builder().baseUrl(digioApiConfig.apiUrl).build();
    }

}
