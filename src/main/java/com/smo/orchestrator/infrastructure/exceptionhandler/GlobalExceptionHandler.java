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

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BussinessException.class)
    public Mono<ResponseEntity<Mono<AnswerException>>> handleBussinessException(BussinessException exception, ServerWebExchange exchange) {
        return logAndBuildResponse(LOG_EXCEPTION_BUSSINESS_EXCEPTION, exception.getStatus(), exception.getDetail(), exchange);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Mono<AnswerException>>> handleIllegalArgumentException(IllegalArgumentException exception, ServerWebExchange exchange) {
        return logAndBuildResponse(LOG_EXCEPTION_ILLEGAL_ARGUMENT_EXCEPTION, HttpStatus.BAD_REQUEST, exception.getMessage(), exchange);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public Mono<ResponseEntity<Mono<AnswerException>>> handleNoResourceFoundException(NoResourceFoundException exception, ServerWebExchange exchange) {
        return logAndBuildResponse(LOG_EXCEPTION_NO_RESOURCE_FOUND_EXCEPTION, HttpStatus.NOT_FOUND, exception.getReason(), exchange);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<Mono<AnswerException>>> handleResponseStatusException(ResponseStatusException exception, ServerWebExchange exchange) {
        return logAndBuildResponse(LOG_EXCEPTION_RESPONSE_STATUS_EXCEPTION, HttpStatus.valueOf(exception.getStatusCode().value()), exception.getReason(), exchange);
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Mono<AnswerException>>> handleGeneralException(Exception exception, ServerWebExchange exchange) {
        return logAndBuildResponse(LOG_EXCEPTION_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exchange);
    }

    private String getMessageId(ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().getFirst(REQUEST_HEADER_MESSAGE_ID);
    }

    private Mono<ResponseEntity<Mono<AnswerException>>> logAndBuildResponse(String logMessage, HttpStatus status, String errorDetail, ServerWebExchange exchange) {
        String messageId = getMessageId(exchange);
        log.error(logMessage, messageId, errorDetail);
        return Mono.just(ResponseEntity.status(status)
                .body(Mono.just(new AnswerException(String.valueOf(status.value()), errorDetail))));
    }
}
