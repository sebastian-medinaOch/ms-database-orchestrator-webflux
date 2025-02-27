package com.smo.orchestrator.application.services.interfaces;

import reactor.core.publisher.Mono;

public interface IGetSimilarProductsIds {
    Mono<Object> get(String productId);
}
