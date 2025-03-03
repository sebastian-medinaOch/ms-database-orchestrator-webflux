package com.smo.orchestrator.application.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smo.orchestrator.application.response.DataResponse;
import com.smo.orchestrator.application.services.interfaces.IGetDetailProductId;
import com.smo.orchestrator.domain.ports.in.IGetDetailProductIdUseCaseIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class GetDetailProductIdService implements IGetDetailProductId {

    private final IGetDetailProductIdUseCaseIn iGetDetailProductIdUseCaseIn;
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Object> get(String productId) {
        return iGetDetailProductIdUseCaseIn.get(productId)
                .map(products -> objectMapper.convertValue(products, DataResponse.class).getData());
    }

}
