package com.smo.orchestrator.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_ANSWER_DATA_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_DATA_RESPONSE_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_DATA_RESPONSE_EXAMPLE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = SWAGGER_ANSWER_DATA_DESCRIPTION)
public class AnswerData {

    @Schema(description = SWAGGER_DATA_RESPONSE_DESCRIPTION, example = SWAGGER_DATA_RESPONSE_EXAMPLE)
    private DataResponse dataResponse;

}
