package com.smo.orchestrator.domain.models.response;

import lombok.Builder;
import lombok.Data;

/**
 * Representa la estructura de respuesta de datos en la aplicación.
 *
 * <p>Encapsula los datos de respuesta dentro de un objeto genérico.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@Data
@Builder
public class DataResponseModel {

    /**
     * Datos contenidos en la respuesta.
     */
    private Object data;

}
