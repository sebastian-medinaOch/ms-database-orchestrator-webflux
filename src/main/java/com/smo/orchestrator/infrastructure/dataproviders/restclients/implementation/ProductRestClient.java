package com.smo.orchestrator.infrastructure.dataproviders.restclients.implementation;

import com.smo.orchestrator.domain.ports.on.IGetSimilarProductsIdsUseCaseOn;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ProductRestClient implements IGetSimilarProductsIdsUseCaseOn {

    private final Environment environment;
    private final WebClient webClientBuilder;

    @Override
    public Flux<Integer> getProducts(String productId) {
        return webClientBuilder
                .get()
                .uri("/product/{productId}/similarids", productId) // URL con parámetro dinámico
                .retrieve()
                .bodyToFlux(Integer.class) // El servicio devuelve una lista de enteros
                .switchIfEmpty(Flux.empty()); //
    }


   /* private Mono<ProductResponse> getProductDetailId(String productId) {
        return webClientBuilder
                .baseUrl("http://localhost:3001")
                .build()
                .get()
                .uri("/product/{productId}", productId)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .switchIfEmpty(Mono.error(new Throwable()));

    }*/

}
