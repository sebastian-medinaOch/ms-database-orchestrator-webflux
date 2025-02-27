package com.smo.orchestrator.application.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smo.orchestrator.application.response.DataResponse;
import com.smo.orchestrator.application.services.interfaces.IGetSimilarProductsIds;
import com.smo.orchestrator.domain.ports.in.IGetSimilarProductsIdsUseCaseIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class GetSimilarProductsIdsService implements IGetSimilarProductsIds {

    private final IGetSimilarProductsIdsUseCaseIn iGetSimilarProductsIdsUseCaseIn;
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Object> get(String productId) {
        return iGetSimilarProductsIdsUseCaseIn.get(productId)
                .map(products -> objectMapper.convertValue(products, DataResponse.class));
    }

}
