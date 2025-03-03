package com.smo.orchestrator.application.services.interfaces;

import reactor.core.publisher.Flux;

/**
 * Interfaz que define el contrato para obtener productos similares a un producto dado.
 *
 * <p>Implementaciones de esta interfaz deben proporcionar la lógica necesaria
 * para recuperar una lista de productos similares.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
public interface IGetSimilarProductsIds {

    /**
     * Obtiene una lista de productos similares a un producto dado.
     *
     * @param productId identificador único del producto base.
     * @return un {@link Flux} que emite los productos similares.
     */
    Flux<Object> get(String productId);

}
