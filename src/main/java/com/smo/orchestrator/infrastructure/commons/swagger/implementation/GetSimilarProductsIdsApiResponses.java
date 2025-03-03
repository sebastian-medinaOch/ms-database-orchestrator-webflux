package com.smo.orchestrator.infrastructure.commons.swagger.implementation;

import com.smo.orchestrator.application.response.AnswerData;
import com.smo.orchestrator.domain.exceptions.AnswerException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_ANSWER_DATA_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_ANSWER_EXCEPTION_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_API_RESPONSE_COMMON_API_400;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_API_RESPONSE_COMMON_API_500;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OPERATIONS_PRODUCTS_API_RESPONSE_200;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.UTILITY_CODE_STATUS_200;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.UTILITY_CODE_STATUS_400;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.UTILITY_CODE_STATUS_500;


/**
 * Anotación personalizada que agrupa las respuestas de API comunes,
 * evitando repetirlas en cada método del controlador.
 * <p>
 * Incluye:
 * - 200 OK
 * - 204 No Content
 * - 400 Bad Request
 * - 401 Unauthorized
 * - 403 Forbidden
 * - 404 Not Found
 * - 408 Request Timeout
 * - 409 Conflict
 * - 500 Internal Server Error
 * - 501 Not Implemented
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ApiResponses(value = {
        @ApiResponse(
                responseCode = UTILITY_CODE_STATUS_200,
                description = SWAGGER_OPERATIONS_PRODUCTS_API_RESPONSE_200,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = AnswerData.class, description = SWAGGER_ANSWER_DATA_DESCRIPTION),
                        examples = @ExampleObject(GetSimilarProductsIdsResponsesExamples.SUCCESS_200_EXAMPLE)
                )
        ),
        @ApiResponse(
                responseCode = UTILITY_CODE_STATUS_400,
                description = SWAGGER_API_RESPONSE_COMMON_API_400,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = AnswerException.class, description = SWAGGER_ANSWER_EXCEPTION_DESCRIPTION),
                        examples = @ExampleObject(GetSimilarProductsIdsResponsesExamples.ERROR_400_EXAMPLE)
                )
        ),
        @ApiResponse(
                responseCode = UTILITY_CODE_STATUS_500,
                description = SWAGGER_API_RESPONSE_COMMON_API_500,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = AnswerException.class, description = SWAGGER_ANSWER_EXCEPTION_DESCRIPTION),
                        examples = @ExampleObject(GetSimilarProductsIdsResponsesExamples.ERROR_500_EXAMPLE)
                )
        )
})
public @interface GetSimilarProductsIdsApiResponses {
}
