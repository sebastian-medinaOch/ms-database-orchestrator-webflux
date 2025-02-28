package com.smo.orchestrator.infrastructure.dataproviders.restclients.implementation;

import com.smo.orchestrator.domain.ports.on.IGetSimilarProductsIdsUseCaseOn;
import com.smo.orchestrator.infrastructure.dataproviders.restclients.response.ProductResponse;
import com.smo.orchestrator.infrastructure.dataproviders.restclients.response.SimilarProductsIdsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductRestClient implements IGetSimilarProductsIdsUseCaseOn {

    private final Environment environment;
    private final WebClient.Builder webClientBuilder;

    @Override
    public Flux<SimilarProductsIdsResponse> getProducts(String productId) {
        return webClientBuilder
                .baseUrl("http://localhost:3001") // Define la URL base
                .build()
                .get()
                .uri("/product/{productId}/similarids", productId) // Endpoint dinámico con el ID
                .retrieve()
                .bodyToFlux(SimilarProductsIdsResponse.class)
                .switchIfEmpty(Mono.error(new Throwable()));
    }


    private Mono<ProductResponse> getProductDetailId(String productId) {
        return webClientBuilder
                .baseUrl("http://localhost:3001")
                .build()
                .get()
                .uri("/product/{productId}", productId)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .switchIfEmpty(Mono.error(new Throwable()));

    }

}
