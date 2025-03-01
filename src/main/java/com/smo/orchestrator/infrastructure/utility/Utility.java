package com.smo.orchestrator.infrastructure.utility;

import com.smo.orchestrator.domain.exceptions.BussinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Utility {

    public Mono<Void> validateData(String productId, String messageId) {
        if (isNullOrEmpty(productId)) {
            return Mono.error(new BussinessException(HttpStatus.BAD_REQUEST, "El parámetro 'productId' es requerido"));
        }
        if (isNullOrEmpty(messageId)) {
            return Mono.error(new BussinessException(HttpStatus.BAD_REQUEST, "El header 'messageId' es requerido"));
        }
        return Mono.empty();
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

}
