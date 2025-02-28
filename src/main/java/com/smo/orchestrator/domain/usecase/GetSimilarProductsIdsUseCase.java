package com.smo.orchestrator.domain.usecase;

import com.smo.orchestrator.domain.models.response.DataResponseModel;
import com.smo.orchestrator.domain.ports.in.IGetSimilarProductsIdsUseCaseIn;
import com.smo.orchestrator.domain.ports.on.IGetSimilarProductsIdsUseCaseOn;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetSimilarProductsIdsUseCase implements IGetSimilarProductsIdsUseCaseIn {

    private final IGetSimilarProductsIdsUseCaseOn iGetSimilarProductsIdsUseCaseOn;

    @Override
    public Flux<DataResponseModel> get(String productId) {
        return iGetSimilarProductsIdsUseCaseOn.getProducts(productId)
                .map(data -> DataResponseModel.builder().data(data).build());
    }


}
