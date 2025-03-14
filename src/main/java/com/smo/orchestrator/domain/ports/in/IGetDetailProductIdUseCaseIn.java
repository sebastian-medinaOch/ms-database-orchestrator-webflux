package com.smo.orchestrator.domain.ports.in;

import reactor.core.publisher.Mono;

/**
 * Interfaz que define el caso de uso para obtener el detalle de un producto.
 *
 * <p>Las implementaciones de esta interfaz deben proporcionar la lógica necesaria
 * para recuperar la información de un producto basado en su identificador.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
public interface IGetDetailProductIdUseCaseIn {

    /**
     * Obtiene el detalle de un producto basado en su identificador.
     *
     * @param productId identificador único del producto.
     * @return un {@link Mono} que emite el detalle del producto.
     */
    Mono<Object> get(String productId);

}
