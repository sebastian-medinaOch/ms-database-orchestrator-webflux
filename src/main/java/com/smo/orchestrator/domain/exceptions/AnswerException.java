package com.smo.orchestrator.domain.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_ANSWER_EXCEPTION_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_ANSWER_EXCEPTION_MESSAGE_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_ANSWER_EXCEPTION_STATUS_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_API_RESPONSE_COMMON_API_400;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.UTILITY_CODE_STATUS_400;

/**
 * Representa la estructura estándar para respuestas de error en la aplicación.
 *
 * <p>Esta clase encapsula el código de estado HTTP y el mensaje de error que se devuelve
 * cuando ocurre una excepción en la aplicación.</p>
 *
 * <p>Está documentada con Swagger para su uso en la API.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = SWAGGER_ANSWER_EXCEPTION_DESCRIPTION)
public class AnswerException {

    /**
     * Código de estado HTTP del error.
     */
    @Schema(description = SWAGGER_ANSWER_EXCEPTION_STATUS_DESCRIPTION, example = UTILITY_CODE_STATUS_400)
    private String status;
    /**
     * Mensaje detallado de la excepción.
     */
    @Schema(description = SWAGGER_ANSWER_EXCEPTION_MESSAGE_DESCRIPTION,
            example = SWAGGER_API_RESPONSE_COMMON_API_400)
    private String message;

}
