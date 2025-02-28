package com.smo.orchestrator.domain.ports.on;

import com.smo.orchestrator.infrastructure.dataproviders.restclients.response.SimilarProductsIdsResponse;
import reactor.core.publisher.Flux;

public interface IGetSimilarProductsIdsUseCaseOn {

    Flux<SimilarProductsIdsResponse> getProducts(String productId);

}
