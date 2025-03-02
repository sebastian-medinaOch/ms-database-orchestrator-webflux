package com.smo.orchestrator.infrastructure.dataproviders.restclients.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smo.orchestrator.domain.exceptions.BussinessException;
import com.smo.orchestrator.domain.models.response.ProductResponseModel;
import com.smo.orchestrator.domain.ports.on.IProductRestOn;
import com.smo.orchestrator.infrastructure.dataproviders.restclients.response.ProductResponseClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductRestRestClient implements IProductRestOn {

    private final Environment environment;
    private final WebClient webClientBuilder;
    private final ObjectMapper objectMapper;

    @Override
    public Flux<Integer> getProducts(String productId) {
        return webClientBuilder
                .get()
                .uri("/product/{productId}/similarids", productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        response.statusCode().equals(HttpStatus.NOT_FOUND)
                                ? Mono.error(new BussinessException(HttpStatus.NOT_FOUND, "No se encontró ningun regristro bajo ese productId"))
                                : response.createException().flatMap(Mono::error)
                )
                .bodyToFlux(Integer.class)
                .switchIfEmpty(Flux.empty()); //
    }

    public Mono<ProductResponseModel> getProduct(String productId) {
        return webClientBuilder
                .get()
                .uri("/product/{productId}", productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        response.statusCode().equals(HttpStatus.NOT_FOUND)
                                ? Mono.error(new BussinessException(HttpStatus.NOT_FOUND, "No se encontró ningun regristro bajo ese productId"))
                                : response.createException().flatMap(Mono::error)
                )
                .bodyToMono(ProductResponseClient.class)
                .map(data -> objectMapper.convertValue(data, ProductResponseModel.class))
                .switchIfEmpty(Mono.empty());
    }

}
