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
import reactor.core.publisher.Mono;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_EXCEPTION_BUSSINESS_EXCEPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_EXCEPTION_EXCEPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_EXCEPTION_ILLEGAL_ARGUMENT_EXCEPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_EXCEPTION_NO_RESOURCE_FOUND_EXCEPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.LOG_EXCEPTION_RESPONSE_STATUS_EXCEPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.MESSAGE_ERROR_EXCEPTION;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<Mono<AnswerException>> handleBussinessException(BussinessException exception) {
        log.error(LOG_EXCEPTION_BUSSINESS_EXCEPTION, exception.getDetail());
        return ResponseEntity
                .status(exception.getStatus())
                .body(Mono.just(new AnswerException(String.valueOf(exception.getStatus().value()), exception.getDetail())));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Mono<AnswerException>> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error(LOG_EXCEPTION_ILLEGAL_ARGUMENT_EXCEPTION, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Mono.just(new AnswerException(String.valueOf(HttpStatus.BAD_REQUEST.value()), exception.getMessage())));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Mono<AnswerException>> handleNoResourceFoundException(NoResourceFoundException exception) {
        log.error(LOG_EXCEPTION_NO_RESOURCE_FOUND_EXCEPTION, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Mono.just(new AnswerException(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getReason())));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Mono<AnswerException>> handleResponseStatusException(ResponseStatusException exception) {
        log.error(LOG_EXCEPTION_RESPONSE_STATUS_EXCEPTION, exception.getMessage());
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(Mono.just(new AnswerException(String.valueOf(exception.getStatusCode().value()), exception.getReason())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Mono<AnswerException>> handleGeneralException(Exception exception) {
        log.error(LOG_EXCEPTION_EXCEPTION, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Mono.just(new AnswerException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), MESSAGE_ERROR_EXCEPTION)));
    }

}
