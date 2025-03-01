package com.smo.orchestrator.infrastructure.controllers;

import com.smo.orchestrator.application.response.AnswerData;
import com.smo.orchestrator.application.response.DataResponse;
import com.smo.orchestrator.application.services.interfaces.IGetSimilarProductsIds;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Log4j2
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class EndpointGetSimilarProductsIds {

    private final IGetSimilarProductsIds iGetSimilarProductsIds;

    @GetMapping(value = "/{productId}/similarids")
    public Flux<Object> get(@PathVariable("productId") String productId,
                            @RequestHeader(value = "messageId") String messageId) {
        return iGetSimilarProductsIds.get(productId)
                .map(data -> new AnswerData(DataResponse.builder().data(data).build()).getDataResponse().getData());
    }

}
