package com.smo.orchestrator.domain.ports.on;

import com.smo.orchestrator.domain.models.response.ProductResponseModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz que define los métodos para obtener información de productos desde un proveedor externo.
 *
 * <p>Las implementaciones de esta interfaz deben proporcionar la lógica necesaria
 * para recuperar productos y sus identificadores de productos similares.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
public interface IProductRestOn {

    /**
     * Obtiene una lista de identificadores de productos similares a un producto dado.
     *
     * @param productId identificador único del producto base.
     * @return un {@link Flux} que emite los IDs de los productos similares.
     */
    Flux<Integer> getProducts(String productId);

    /**
     * Obtiene el detalle de un producto específico basado en su identificador.
     *
     * @param productId identificador único del producto.
     * @return un {@link Mono} que emite el objeto {@link ProductResponseModel} con la información del producto.
     */
    Mono<ProductResponseModel> getProduct(String productId);

}
