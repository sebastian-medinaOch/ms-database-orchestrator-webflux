package com.smo.orchestrator.domain.ports.in;

import com.smo.orchestrator.domain.models.response.DataResponseModel;
import reactor.core.publisher.Mono;

public interface IGetSimilarProductsIdsUseCaseIn {
    Mono<DataResponseModel> get(String productId);
}
