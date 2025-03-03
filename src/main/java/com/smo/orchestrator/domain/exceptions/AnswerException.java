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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = SWAGGER_ANSWER_EXCEPTION_DESCRIPTION)
public class AnswerException {

    @Schema(description = SWAGGER_ANSWER_EXCEPTION_STATUS_DESCRIPTION, example = UTILITY_CODE_STATUS_400)
    private String status;
    @Schema(description = SWAGGER_ANSWER_EXCEPTION_MESSAGE_DESCRIPTION,
            example = SWAGGER_API_RESPONSE_COMMON_API_400)
    private String message;

}
