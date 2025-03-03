package com.smo.orchestrator.domain.ports.in;

import com.smo.orchestrator.domain.models.response.DataResponseModel;
import reactor.core.publisher.Flux;

/**
 * Interfaz que define el caso de uso para obtener productos similares a un producto dado.
 *
 * <p>Las implementaciones de esta interfaz deben proporcionar la lógica necesaria
 * para recuperar una lista de productos similares.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
public interface IGetSimilarProductsIdsUseCaseIn {

    /**
     * Obtiene una lista de productos similares a un producto dado.
     *
     * @param productId identificador único del producto base.
     * @return un {@link Flux} que emite los productos similares encapsulados en {@link DataResponseModel}.
     */
    Flux<DataResponseModel> get(String productId);

}
