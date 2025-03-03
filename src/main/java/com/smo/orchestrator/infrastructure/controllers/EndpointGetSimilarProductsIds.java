package com.smo.orchestrator.infrastructure.controllers;

import com.smo.orchestrator.application.response.AnswerData;
import com.smo.orchestrator.application.response.DataResponse;
import com.smo.orchestrator.application.services.interfaces.IGetSimilarProductsIds;
import com.smo.orchestrator.infrastructure.commons.swagger.implementation.GetSimilarProductsIdsApiResponses;
import com.smo.orchestrator.infrastructure.commons.utility.Utility;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_INFO_CONTROLLER_GET_SIMILAR_PRODUCTS_IDS;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_INFO_CONTROLLER_GET_SIMILAR_PRODUCTS_IDS_SUCCESS;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.PATH_GET_SIMILAR_PRODUCTS_IDS_CONTROLLER;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.PATH_PRODUCT_CONTROLLER;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.PATH_VARIABLE_PRODUCT_ID;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.REQUEST_HEADER_MESSAGE_ID;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_GET_SIMILAR_PRODUCTS_IDS;

@Log4j2
@RestController
@RequestMapping(PATH_PRODUCT_CONTROLLER)
@RequiredArgsConstructor
public class EndpointGetSimilarProductsIds {

    private final IGetSimilarProductsIds iGetSimilarProductsIds;
    private final Utility utility;

    @GetMapping(value = PATH_GET_SIMILAR_PRODUCTS_IDS_CONTROLLER)
    @Operation(summary = SWAGGER_GET_SIMILAR_PRODUCTS_IDS)
    @GetSimilarProductsIdsApiResponses
    public Flux<Object> getSimilarProductsIds(@PathVariable(PATH_VARIABLE_PRODUCT_ID) String productId,
                                              @RequestHeader(value = REQUEST_HEADER_MESSAGE_ID) String messageId) {

        return utility.validateData(productId, messageId)
                .doFirst(() -> log.info(LOG_INFO_CONTROLLER_GET_SIMILAR_PRODUCTS_IDS, messageId, productId))
                .thenMany(iGetSimilarProductsIds.get(productId))
                .map(data -> new AnswerData(DataResponse.builder().data(data).build()).getDataResponse().getData())
                .doOnComplete(() -> log.info(LOG_INFO_CONTROLLER_GET_SIMILAR_PRODUCTS_IDS_SUCCESS, messageId, productId));
    }

}
