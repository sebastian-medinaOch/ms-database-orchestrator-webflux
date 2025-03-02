package com.smo.orchestrator.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.CONFIG_BASE_URL_WEB_CLIENT_PRODUCT;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient createWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(CONFIG_BASE_URL_WEB_CLIENT_PRODUCT)
                .build();
    }

}
