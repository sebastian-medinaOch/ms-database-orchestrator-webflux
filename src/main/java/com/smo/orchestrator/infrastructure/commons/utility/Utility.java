package com.smo.orchestrator.infrastructure.commons.utility;

import com.smo.orchestrator.domain.exceptions.BussinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.MESSAGE_ERROR_MESSAGE_ID_EMPTY;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.MESSAGE_ERROR_PRODUCT_ID_EMPTY;

@Component
public class Utility {

    public Mono<Void> validateData(String productId, String messageId) {
        if (isNullOrEmpty(productId)) {
            return Mono.error(new BussinessException(HttpStatus.BAD_REQUEST, MESSAGE_ERROR_PRODUCT_ID_EMPTY));
        }
        if (isNullOrEmpty(messageId)) {
            return Mono.error(new BussinessException(HttpStatus.BAD_REQUEST, MESSAGE_ERROR_MESSAGE_ID_EMPTY));
        }
        return Mono.empty();
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

}
