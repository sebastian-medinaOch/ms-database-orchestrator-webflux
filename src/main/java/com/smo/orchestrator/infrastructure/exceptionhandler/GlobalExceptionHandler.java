package com.smo.orchestrator.infrastructure.exceptionhandler;

import com.smo.orchestrator.domain.exceptions.AnswerException;
import com.smo.orchestrator.domain.exceptions.BussinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_EXCEPTION_BUSSINESS_EXCEPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_EXCEPTION_EXCEPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_EXCEPTION_ILLEGAL_ARGUMENT_EXCEPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_EXCEPTION_NO_RESOURCE_FOUND_EXCEPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_EXCEPTION_RESPONSE_STATUS_EXCEPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.REQUEST_HEADER_MESSAGE_ID;

/**
 * Manejador global de excepciones para la aplicación.
 *
 * <p>Esta clase captura y maneja diferentes tipos de excepciones en la aplicación,
 * proporcionando respuestas estructuradas con códigos de estado HTTP adecuados y
 * registrando los errores en el log.</p>
 *
 * <p>Utiliza {@code @RestControllerAdvice} para aplicar el manejo de excepciones de forma global,
 * y {@code @ExceptionHandler} en cada método para manejar excepciones específicas.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de tipo {@link BussinessException}.
     *
     * @param exception la excepción lanzada en el flujo de negocio.
     * @param exchange  el contexto de la solicitud.
     * @return un {@link Mono} que emite una {@link ResponseEntity} con el código de estado
     *         y el mensaje de error correspondiente.
     */
    @ExceptionHandler(BussinessException.class)
    public Mono<ResponseEntity<Mono<AnswerException>>> handleBussinessException(BussinessException exception, ServerWebExchange exchange) {
        return logAndBuildResponse(LOG_EXCEPTION_BUSSINESS_EXCEPTION, exception.getStatus(), exception.getDetail(), exchange);
    }

    /**
     * Maneja excepciones de tipo {@link IllegalArgumentException}.
     *
     * @param exception la excepción de argumento ilegal.
     * @param exchange  el contexto de la solicitud.
     * @return un {@link Mono} que emite una {@link ResponseEntity} con el código de estado
     *         {@link HttpStatus#BAD_REQUEST} y el mensaje de error.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Mono<AnswerException>>> handleIllegalArgumentException(IllegalArgumentException exception, ServerWebExchange exchange) {
        return logAndBuildResponse(LOG_EXCEPTION_ILLEGAL_ARGUMENT_EXCEPTION, HttpStatus.BAD_REQUEST, exception.getMessage(), exchange);
    }

    /**
     * Maneja excepciones de tipo {@link NoResourceFoundException}.
     *
     * @param exception la excepción lanzada cuando no se encuentra un recurso solicitado.
     * @param exchange  el contexto de la solicitud.
     * @return un {@link Mono} que emite una {@link ResponseEntity} con el código de estado
     *         {@link HttpStatus#NOT_FOUND} y el mensaje de error.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public Mono<ResponseEntity<Mono<AnswerException>>> handleNoResourceFoundException(NoResourceFoundException exception, ServerWebExchange exchange) {
        return logAndBuildResponse(LOG_EXCEPTION_NO_RESOURCE_FOUND_EXCEPTION, HttpStatus.NOT_FOUND, exception.getReason(), exchange);
    }

    /**
     * Maneja excepciones de tipo {@link ResponseStatusException}.
     *
     * @param exception la excepción lanzada cuando ocurre un error HTTP específico.
     * @param exchange  el contexto de la solicitud.
     * @return un {@link Mono} que emite una {@link ResponseEntity} con el código de estado
     *         correspondiente y el mensaje de error.
     */
    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<Mono<AnswerException>>> handleResponseStatusException(ResponseStatusException exception, ServerWebExchange exchange) {
        return logAndBuildResponse(LOG_EXCEPTION_RESPONSE_STATUS_EXCEPTION, HttpStatus.valueOf(exception.getStatusCode().value()), exception.getReason(), exchange);
    }

    /**
     * Maneja excepciones generales de tipo {@link Exception}.
     *
     * @param exception la excepción desconocida capturada.
     * @param exchange  el contexto de la solicitud.
     * @return un {@link Mono} que emite una {@link ResponseEntity} con el código de estado
     *         {@link HttpStatus#INTERNAL_SERVER_ERROR} y el mensaje de error.
     */
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Mono<AnswerException>>> handleGeneralException(Exception exception, ServerWebExchange exchange) {
        return logAndBuildResponse(LOG_EXCEPTION_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exchange);
    }

    /**
     * Obtiene el identificador de mensaje desde los headers de la solicitud.
     *
     * @param exchange el contexto de la solicitud.
     * @return el identificador de mensaje o {@code null} si no está presente en los headers.
     */
    private String getMessageId(ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().getFirst(REQUEST_HEADER_MESSAGE_ID);
    }

    /**
     * Registra el error en el log y construye la respuesta con el código de estado y mensaje de error.
     *
     * @param logMessage  mensaje de log específico de la excepción.
     * @param status      código de estado HTTP de la respuesta.
     * @param errorDetail detalle del error ocurrido.
     * @param exchange    el contexto de la solicitud.
     * @return un {@link Mono} con una {@link ResponseEntity} que contiene un {@link AnswerException}.
     */
    private Mono<ResponseEntity<Mono<AnswerException>>> logAndBuildResponse(String logMessage, HttpStatus status, String errorDetail, ServerWebExchange exchange) {
        String messageId = getMessageId(exchange);
        log.error(logMessage, messageId, errorDetail);
        return Mono.just(ResponseEntity.status(status)
                .body(Mono.just(new AnswerException(String.valueOf(status.value()), errorDetail))));
    }
}
