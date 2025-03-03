package com.smo.orchestrator.application.services.interfaces;

import reactor.core.publisher.Flux;

public interface IGetSimilarProductsIds {
    Flux<Object> get(String productId);
}
