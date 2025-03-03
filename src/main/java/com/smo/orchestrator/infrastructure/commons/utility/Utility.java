package com.smo.orchestrator.infrastructure.commons.utility;

import com.smo.orchestrator.domain.exceptions.BussinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.MESSAGE_ERROR_MESSAGE_ID_EMPTY;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.MESSAGE_ERROR_PRODUCT_ID_EMPTY;

/**
 * Clase de utilidades para validaciones comunes en la aplicación.
 *
 * <p>Proporciona métodos para la validación de datos de entrada antes de ser procesados
 * por los controladores y servicios. En caso de valores inválidos, lanza una excepción
 * de tipo {@link BussinessException} con el código de estado HTTP correspondiente.</p>
 *
 * <p>Se marca con {@code @Component} para ser gestionada como un bean por Spring.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@Component
public class Utility {

    /**
     * Valida que los valores de {@code productId} y {@code messageId} no sean nulos ni vacíos.
     * <p>
     * Si alguno de los valores es inválido, se devuelve un {@link Mono#error} con una excepción
     * de tipo {@link BussinessException}, indicando un error de solicitud incorrecta ({@code 400 Bad Request}).
     * </p>
     *
     * @param productId identificador único del producto a validar.
     * @param messageId identificador único del mensaje a validar.
     * @return un {@link Mono<Void>} vacío si la validación es exitosa, o un {@link Mono#error} si hay errores.
     */
    public Mono<Void> validateData(String productId, String messageId) {
        if (isNullOrEmpty(productId)) {
            return Mono.error(new BussinessException(HttpStatus.BAD_REQUEST, MESSAGE_ERROR_PRODUCT_ID_EMPTY));
        }
        if (isNullOrEmpty(messageId)) {
            return Mono.error(new BussinessException(HttpStatus.BAD_REQUEST, MESSAGE_ERROR_MESSAGE_ID_EMPTY));
        }
        return Mono.empty();
    }

    /**
     * Verifica si una cadena es nula o está vacía después de recortar espacios en blanco.
     *
     * @param value la cadena a evaluar.
     * @return {@code true} si la cadena es nula o vacía, {@code false} en caso contrario.
     */
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

}
