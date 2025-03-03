package com.smo.orchestrator.infrastructure.controllers;

import com.smo.orchestrator.application.response.AnswerData;
import com.smo.orchestrator.application.response.DataResponse;
import com.smo.orchestrator.application.services.interfaces.IGetDetailProductId;
import com.smo.orchestrator.infrastructure.commons.swagger.implementation.GetProductIdDetailApiResponses;
import com.smo.orchestrator.infrastructure.commons.utility.Utility;
import io.swagger.v3.oas.annotations.Operation;
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
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.REQUEST_HEADER_MESSAGE_ID;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_GET_DETAIL_PRODUCT_ID;

/**
 * Controlador REST que expone el endpoint para obtener el detalle de un producto mediante su ID,
 * utilizando programación reactiva con Spring WebFlux.
 *
 * <p>Esta clase realiza la validación de los datos de entrada y registra eventos en el log,
 * antes y después de obtener la información. Retorna un {@link Mono} que emite el objeto con el detalle
 * del producto o con exception controlada en caso de no encontrar información.</p>
 *
 * <p>La inyección de dependencias se realiza mediante el uso de {@code @RequiredArgsConstructor},
 * por lo que no es necesario declarar explícitamente el constructor.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@Log4j2
@RestController
@RequestMapping(PATH_PRODUCT_CONTROLLER)
@RequiredArgsConstructor
public class EndpointGetDetailProductId {

    private final IGetDetailProductId iGetDetailProductId;
    private final Utility utility;

    /**
     * Obtiene, de forma reactiva, el detalle de un producto mediante su identificador.
     * <p>
     * Primero valida los datos de entrada (ID de producto y ID de mensaje). En caso de pasar la validación,
     * invoca al servicio que realiza la obtención del detalle y construye la respuesta en un objeto
     * de tipo {@code AnswerData}, retornando un {@link Mono} con el resultado.
     * </p>
     *
     * @param productId  identificador único del producto (se inyecta desde la URL del endpoint)
     * @param messageId  identificador del mensaje (se inyecta desde el encabezado HTTP)
     * @return un {@link Mono} que emite el detalle del producto o vacío en caso de no existir
     * @see IGetDetailProductId
     */
    @GetMapping(value = PATH_GET_DETAIL_PRODUCT_ID_CONTROLLER)
    @Operation(summary = SWAGGER_GET_DETAIL_PRODUCT_ID)
    @GetProductIdDetailApiResponses
    public Mono<Object> getDetailProductId(@PathVariable(PATH_VARIABLE_PRODUCT_ID) String productId,
                                           @RequestHeader(value = REQUEST_HEADER_MESSAGE_ID) String messageId) {

        return utility.validateData(productId, messageId)
                .doFirst(() -> log.info(LOG_INFO_CONTROLLER_GET_DETAIL_PRODUCT_ID, messageId, productId))
                .then(iGetDetailProductId.get(productId))
                .map(data -> new AnswerData(DataResponse.builder().data(data).build()).getDataResponse().getData())
                .doOnSuccess(data -> log.info(LOG_INFO_CONTROLLER_GET_DETAIL_PRODUCT_ID_SUCCESS, messageId, productId));
    }

}
