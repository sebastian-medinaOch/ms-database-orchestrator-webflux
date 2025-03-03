package com.smo.orchestrator.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
public class BussinessException extends Exception {

    private final HttpStatus status;
    private final String detail;

}
