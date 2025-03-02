package com.smo.orchestrator.infrastructure.dataproviders.restclients.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smo.orchestrator.domain.exceptions.BussinessException;
import com.smo.orchestrator.domain.models.response.ProductResponseModel;
import com.smo.orchestrator.domain.ports.on.IProductRestOn;
import com.smo.orchestrator.infrastructure.dataproviders.restclients.response.ProductResponseClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.CONFIG_CACHE_PRODUCT;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.CONFIG_URI_GET_PRODUCTS_WEB_CLIENT;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.CONFIG_URI_GET_PRODUCT_WEB_CLIENT;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.MESSAGE_ERROR_GET_PRODUCT_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class ProductRestRestClient implements IProductRestOn {

    private final WebClient webClientBuilder;
    private final ObjectMapper objectMapper;

    @Override
    @Cacheable(value = CONFIG_CACHE_PRODUCT)
    public Flux<Integer> getProducts(String productId) {
        return webClientBuilder
                .get()
                .uri(CONFIG_URI_GET_PRODUCTS_WEB_CLIENT, productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        response.statusCode().equals(HttpStatus.NOT_FOUND)
                                ? Mono.error(new BussinessException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_GET_PRODUCT_NOT_FOUND))
                                : response.createException().flatMap(Mono::error)
                )
                .bodyToFlux(Integer.class)
                .switchIfEmpty(Flux.empty());
    }

    @Cacheable(value = CONFIG_CACHE_PRODUCT)
    public Mono<ProductResponseModel> getProduct(String productId) {
        return webClientBuilder
                .get()
                .uri(CONFIG_URI_GET_PRODUCT_WEB_CLIENT, productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        response.statusCode().equals(HttpStatus.NOT_FOUND)
                                ? Mono.error(new BussinessException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_GET_PRODUCT_NOT_FOUND))
                                : response.createException().flatMap(Mono::error)
                )
                .bodyToMono(ProductResponseClient.class)
                .map(data -> objectMapper.convertValue(data, ProductResponseModel.class))
                .switchIfEmpty(Mono.empty());
    }

}
