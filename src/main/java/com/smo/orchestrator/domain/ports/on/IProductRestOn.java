package com.smo.orchestrator.domain.ports.on;

import com.smo.orchestrator.domain.models.response.ProductResponseModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductRestOn {

    Flux<Integer> getProducts(String productId);

    Mono<ProductResponseModel> getProduct(String productId);

}
