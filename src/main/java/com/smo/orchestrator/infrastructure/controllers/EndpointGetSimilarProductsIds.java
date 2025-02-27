package com.smo.orchestrator.infrastructure.controllers;

import com.smo.orchestrator.application.response.AnswerData;
import com.smo.orchestrator.application.response.DataResponse;
import com.smo.orchestrator.application.services.interfaces.IGetSimilarProductsIds;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class EndpointGetSimilarProductsIds {

    private final IGetSimilarProductsIds iGetSimilarProductsIds;

    @GetMapping(value = "/{productId}/similarids")
    public Mono<ResponseEntity<?>> get(@RequestParam("productId") String productId,
                                       @RequestHeader(value = "messageId") String messageId) {
        return iGetSimilarProductsIds.get(productId)
                .flatMap(products -> Mono.just(DataResponse.builder().data(Optional.of(products)).build())
                        .flatMap(dataResponse -> Mono.just(new ResponseEntity<>(new AnswerData(dataResponse).getDataResponse(), HttpStatus.OK))));
    }

}
