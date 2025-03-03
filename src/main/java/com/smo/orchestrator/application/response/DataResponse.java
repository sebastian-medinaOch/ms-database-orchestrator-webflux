package com.smo.orchestrator.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_DATA_RESPONSE_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OBJECT_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OBJECT_EXAMPLE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = SWAGGER_DATA_RESPONSE_DESCRIPTION)
public class DataResponse {

    @Schema(description = SWAGGER_OBJECT_DESCRIPTION, example = SWAGGER_OBJECT_EXAMPLE)
    private Object data;

}
