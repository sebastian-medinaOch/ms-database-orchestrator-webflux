package com.smo.orchestrator.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_ANSWER_DATA_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_DATA_RESPONSE_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_DATA_RESPONSE_EXAMPLE;

/**
 * Representa la estructura estándar de respuesta de la aplicación.
 *
 * <p>Contiene un objeto de tipo {@link DataResponse} que encapsula los datos devueltos en la respuesta.</p>
 *
 * <p>Utiliza anotaciones de Swagger para documentar la estructura de respuesta en la API.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = SWAGGER_ANSWER_DATA_DESCRIPTION)
public class AnswerData {

    /**
     * Objeto que contiene los datos de la respuesta.
     */
    @Schema(description = SWAGGER_DATA_RESPONSE_DESCRIPTION, example = SWAGGER_DATA_RESPONSE_EXAMPLE)
    private DataResponse dataResponse;

}
