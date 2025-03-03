package com.smo.orchestrator.infrastructure.dataproviders.restclients.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smo.orchestrator.domain.exceptions.BussinessException;
import com.smo.orchestrator.domain.models.response.ProductResponseModel;
import com.smo.orchestrator.domain.ports.on.IProductRestOn;
import com.smo.orchestrator.infrastructure.dataproviders.restclients.response.ProductResponseClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.CONFIG_CACHE_PRODUCT;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.CONFIG_CACHE_SIMILAR_PRODUCTS_IDS;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.CONFIG_URI_GET_PRODUCTS_WEB_CLIENT;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.CONFIG_URI_GET_PRODUCT_WEB_CLIENT;
import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.MESSAGE_ERROR_GET_PRODUCT_NOT_FOUND;

/**
 * Cliente REST para la obtención de información de productos desde un servicio externo.
 *
 * <p>Esta clase implementa {@link IProductRestOn} y proporciona métodos para recuperar
 * productos y sus IDs similares a través de peticiones HTTP asíncronas usando {@link WebClient}.</p>
 *
 * <p>Las respuestas se almacenan en caché utilizando {@code @Cacheable} para mejorar el rendimiento
 * y reducir la cantidad de llamadas al servicio externo.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class ProductRestRestClient implements IProductRestOn {

    private final WebClient webClientBuilder;
    private final ObjectMapper objectMapper;

    /**
     * Obtiene una lista de identificadores de productos similares a un producto dado.
     * <p>
     * Se realiza una petición GET al servicio externo, y los resultados se almacenan en caché
     * bajo la clave definida en {@code CONFIG_CACHE_SIMILAR_PRODUCTS_IDS}.
     * </p>
     *
     * @param productId identificador del producto base para buscar similares.
     * @return un {@link Flux} de enteros representando los IDs de productos similares.
     */
    @Override
    @Cacheable(value = CONFIG_CACHE_SIMILAR_PRODUCTS_IDS)
    public Flux<Integer> getProducts(String productId) {
        return webClientBuilder
                .get()
                .uri(CONFIG_URI_GET_PRODUCTS_WEB_CLIENT, productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        response.statusCode().equals(HttpStatus.NOT_FOUND)
                                ? Mono.error(new BussinessException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_GET_PRODUCT_NOT_FOUND))
                                : response.createException().flatMap(Mono::error)
                )
                .bodyToFlux(Integer.class)
                .switchIfEmpty(Flux.empty());
    }

    /**
     * Obtiene los detalles de un producto específico basado en su identificador.
     * <p>
     * Se realiza una petición GET al servicio externo, y la respuesta se convierte a un
     * {@link ProductResponseModel}. Si el producto no se encuentra, se lanza una excepción
     * de tipo {@link BussinessException}.
     * </p>
     *
     * @param productId identificador del producto a obtener.
     * @return un {@link Mono} que emite el objeto {@link ProductResponseModel} con la información del producto.
     */
    @Cacheable(value = CONFIG_CACHE_PRODUCT)
    public Mono<ProductResponseModel> getProduct(String productId) {
        return webClientBuilder
                .get()
                .uri(CONFIG_URI_GET_PRODUCT_WEB_CLIENT, productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        response.statusCode().equals(HttpStatus.NOT_FOUND)
                                ? Mono.error(new BussinessException(HttpStatus.NOT_FOUND, MESSAGE_ERROR_GET_PRODUCT_NOT_FOUND))
                                : response.createException().flatMap(Mono::error)
                )
                .bodyToMono(ProductResponseClient.class)
                .map(data -> objectMapper.convertValue(data, ProductResponseModel.class))
                .switchIfEmpty(Mono.empty());
    }

}
