package com.smo.orchestrator.domain.usecase;

import com.smo.orchestrator.domain.models.response.DataResponseModel;
import com.smo.orchestrator.domain.ports.in.IGetSimilarProductsIdsUseCaseIn;
import com.smo.orchestrator.domain.ports.on.IProductRestOn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Component
public class GetSimilarProductsIdsUseCase implements IGetSimilarProductsIdsUseCaseIn {

    private final IProductRestOn iProductRestOn;

    @Override
    public Flux<DataResponseModel> get(String productId) {
        return iProductRestOn.getProducts(productId)
                .map(data -> DataResponseModel.builder().data(data).build());
    }

}
