package com.smo.orchestrator.infrastructure.commons.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OPEN_API_COMPONENTS_INFO_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OPEN_API_COMPONENTS_INFO_TITLE;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OPEN_API_GROUP;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OPEN_API_INFO_CONTACT_EMAIL;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OPEN_API_INFO_CONTACT_NAME;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OPEN_API_INFO_CONTACT_URL;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OPEN_API_INFO_DESCRIPTION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OPEN_API_INFO_TITLE;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OPEN_API_INFO_VERSION;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_OPEN_API_PATHS_TO_MATCH;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.SWAGGER_PACKAGE_INFRASTRUCTURE_CONTROLLERS;

/**
 * Configuración de Swagger para la documentación de la API.
 * <p>
 * Esta clase configura las propiedades globales de la documentación OpenAPI,
 * incluyendo información general y detalles de contacto.
 * </p>
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi() {
        final String[] packagesToScan = {SWAGGER_PACKAGE_INFRASTRUCTURE_CONTROLLERS};
        return GroupedOpenApi
                .builder()
                .group(SWAGGER_OPEN_API_GROUP)
                .packagesToScan(packagesToScan)
                .pathsToMatch(SWAGGER_OPEN_API_PATHS_TO_MATCH)
                .addOpenApiCustomizer(statusApiCostumizer())
                .build();
    }

    private OpenApiCustomizer statusApiCostumizer() {
        return openAPI -> openAPI
                .info(new Info()
                        .title(SWAGGER_OPEN_API_INFO_TITLE)
                        .description(SWAGGER_OPEN_API_INFO_DESCRIPTION)
                        .version(SWAGGER_OPEN_API_INFO_VERSION)
                        .contact(new Contact()
                                .name(SWAGGER_OPEN_API_INFO_CONTACT_NAME)
                                .url(SWAGGER_OPEN_API_INFO_CONTACT_URL)
                                .email(SWAGGER_OPEN_API_INFO_CONTACT_EMAIL)));
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title(SWAGGER_OPEN_API_COMPONENTS_INFO_TITLE).description(
                        SWAGGER_OPEN_API_COMPONENTS_INFO_DESCRIPTION));
    }
}
