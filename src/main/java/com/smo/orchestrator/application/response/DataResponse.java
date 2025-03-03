package com.smo.orchestrator.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_DATA_RESPONSE_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OBJECT_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OBJECT_EXAMPLE;

/**
 * Representa la estructura de datos de la respuesta de la aplicación.
 *
 * <p>Contiene un objeto de tipo {@code Object} que almacena los datos específicos de la respuesta.</p>
 *
 * <p>Utiliza anotaciones de Swagger para documentar la estructura en la API.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = SWAGGER_DATA_RESPONSE_DESCRIPTION)
public class DataResponse {

    /**
     * Datos contenidos en la respuesta.
     */
    @Schema(description = SWAGGER_OBJECT_DESCRIPTION, example = SWAGGER_OBJECT_EXAMPLE)
    private Object data;

}
