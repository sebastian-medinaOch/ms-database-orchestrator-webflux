package com.smo.orchestrator.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.CONFIG_BASE_URL_WEB_CLIENT_PRODUCT;

/**
 * Configuración de {@link WebClient} para la comunicación con servicios externos.
 *
 * <p>Esta clase define un bean de {@link WebClient} que se configura con:
 * <ul>
 *     <li>Cabecera {@code Content-Type} y {@code Accept} en formato JSON.</li>
 *     <li>Una URL base obtenida desde las propiedades del entorno.</li>
 * </ul>
 * </p>
 *
 * <p>La anotación {@code @Configuration} indica que esta clase contiene
 * definiciones de beans para el contexto de Spring.</p>
 *
 * <p>Se usa {@code @RequiredArgsConstructor} para la inyección de dependencias,
 * lo que evita la necesidad de definir un constructor explícito.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final Environment environment;

    /**
     * Crea y configura un bean de {@link WebClient} con cabeceras predeterminadas
     * y una URL base obtenida del archivo de configuración.
     *
     * @return una instancia de {@link WebClient} configurada para realizar solicitudes HTTP.
     * @throws NullPointerException si la propiedad {@code CONFIG_BASE_URL_WEB_CLIENT_PRODUCT} no está definida.
     */
    @Bean
    public WebClient createWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(Objects.requireNonNull(environment.getProperty(CONFIG_BASE_URL_WEB_CLIENT_PRODUCT)))
                .build();
    }

}
