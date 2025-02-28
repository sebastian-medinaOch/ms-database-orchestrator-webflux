package com.smo.orchestrator.domain.ports.in;

import com.smo.orchestrator.domain.models.response.DataResponseModel;
import reactor.core.publisher.Flux;

public interface IGetSimilarProductsIdsUseCaseIn {
    Flux<DataResponseModel> get(String productId);
}
