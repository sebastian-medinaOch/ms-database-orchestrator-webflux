package com.smo.orchestrator.domain.usecase;

import com.smo.orchestrator.domain.models.response.DataResponseModel;
import com.smo.orchestrator.domain.ports.in.IGetDetailProductIdUseCaseIn;
import com.smo.orchestrator.domain.ports.on.IProductRestOn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class GetDetailProductIdUseCase implements IGetDetailProductIdUseCaseIn {

    private final IProductRestOn iProductRestOn;

    @Override
    public Mono<Object> get(String productId) {
        return iProductRestOn.getProduct(productId)
                .map(data -> DataResponseModel.builder().data(data).build());
    }

}
