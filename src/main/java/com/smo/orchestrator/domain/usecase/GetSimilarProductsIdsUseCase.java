package com.smo.orchestrator.domain.usecase;

import com.smo.orchestrator.domain.models.response.DataResponseModel;
import com.smo.orchestrator.domain.ports.in.IGetSimilarProductsIdsUseCaseIn;
import com.smo.orchestrator.domain.ports.on.IGetSimilarProductsIdsUseCaseOn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Component
public class GetSimilarProductsIdsUseCase implements IGetSimilarProductsIdsUseCaseIn {

    private final IGetSimilarProductsIdsUseCaseOn iGetSimilarProductsIdsUseCaseOn;

    @Override
    public Flux<DataResponseModel> get(String productId) {
        return iGetSimilarProductsIdsUseCaseOn.getProducts(productId)
                .map(data -> DataResponseModel.builder().data(data).build());
    }


}
