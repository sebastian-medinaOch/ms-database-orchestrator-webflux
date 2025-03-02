package com.smo.orchestrator.infrastructure.controllers;

import com.smo.orchestrator.application.response.AnswerData;
import com.smo.orchestrator.application.response.DataResponse;
import com.smo.orchestrator.application.services.interfaces.IGetDetailProductId;
import com.smo.orchestrator.infrastructure.commons.utility.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_INFO_CONTROLLER_GET_DETAIL_PRODUCT_ID;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_INFO_CONTROLLER_GET_DETAIL_PRODUCT_ID_SUCCESS;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.PATH_GET_DETAIL_PRODUCT_ID_CONTROLLER;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.PATH_PRODUCT_CONTROLLER;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.PATH_VARIABLE_PRODUCT_ID;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.REQUEST_HEADER_PRODUCT_ID;

@Log4j2
@RestController
@RequestMapping(PATH_PRODUCT_CONTROLLER)
@RequiredArgsConstructor
public class EndpointGetDetailProductId {

    private final IGetDetailProductId iGetDetailProductId;
    private final Utility utility;

    @GetMapping(value = PATH_GET_DETAIL_PRODUCT_ID_CONTROLLER)
    public Mono<Object> getDetailProductId(@PathVariable(PATH_VARIABLE_PRODUCT_ID) String productId,
                                           @RequestHeader(value = REQUEST_HEADER_PRODUCT_ID) String messageId) {

        return utility.validateData(productId, messageId)
                .doFirst(() -> log.info(LOG_INFO_CONTROLLER_GET_DETAIL_PRODUCT_ID, messageId, productId))
                .then(iGetDetailProductId.get(productId))
                .map(data -> new AnswerData(DataResponse.builder().data(data).build()).getDataResponse().getData())
                .doOnSuccess(data -> log.info(LOG_INFO_CONTROLLER_GET_DETAIL_PRODUCT_ID_SUCCESS, messageId, productId));
    }

}
