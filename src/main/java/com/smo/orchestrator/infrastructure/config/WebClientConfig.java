package com.smo.orchestrator.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.CONFIG_BASE_URL_WEB_CLIENT_PRODUCT;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final Environment environment;

    @Bean
    public WebClient createWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(Objects.requireNonNull(environment.getProperty(CONFIG_BASE_URL_WEB_CLIENT_PRODUCT)))
                .build();
    }

}
