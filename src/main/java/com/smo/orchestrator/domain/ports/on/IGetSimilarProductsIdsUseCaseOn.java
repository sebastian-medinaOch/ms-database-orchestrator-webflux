package com.smo.orchestrator.domain.ports.on;

import reactor.core.publisher.Flux;

public interface IGetSimilarProductsIdsUseCaseOn {

    Flux<Integer> getProducts(String productId);

}
