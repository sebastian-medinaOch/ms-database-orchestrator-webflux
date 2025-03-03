package com.smo.orchestrator.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * Excepción personalizada para manejar errores de negocio en la aplicación.
 *
 * <p>Extiende de {@link Exception} y permite definir un código de estado HTTP
 * junto con un mensaje de detalle para describir el error.</p>
 *
 * <p>Utiliza las anotaciones de Lombok para la generación automática de métodos
 * como {@code equals}, {@code hashCode}, y {@code toString}.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
public class BussinessException extends Exception {

    /**
     * Código de estado HTTP asociado a la excepción.
     */
    private final HttpStatus status;
    /**
     * Mensaje detallado del error de negocio ocurrido.
     */
    private final String detail;

}
