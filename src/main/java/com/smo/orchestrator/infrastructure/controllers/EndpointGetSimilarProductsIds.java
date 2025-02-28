package com.smo.orchestrator.infrastructure.controllers;

import com.smo.orchestrator.application.response.AnswerData;
import com.smo.orchestrator.application.response.DataResponse;
import com.smo.orchestrator.application.services.interfaces.IGetSimilarProductsIds;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class EndpointGetSimilarProductsIds {

    private final IGetSimilarProductsIds iGetSimilarProductsIds;

    @GetMapping(value = "/{productId}/similarids")
    public Flux<ResponseEntity<?>> get(@RequestParam("productId") String productId,
                                       @RequestHeader(value = "messageId") String messageId) {
        return iGetSimilarProductsIds.get(productId)
                .flatMap(products -> Mono.just(DataResponse.builder().data(Optional.of(products)).build())
                        .flatMap(dataResponse -> Mono.just(new ResponseEntity<>(new AnswerData(dataResponse).getDataResponse(), HttpStatus.OK))));
    }

}
