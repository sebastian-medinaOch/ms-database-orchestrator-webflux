package com.smo.orchestrator.domain.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la información detallada de un producto.
 *
 * <p>Incluye atributos básicos como ID, nombre, precio y disponibilidad.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseModel {

    /**
     * Identificador único del producto.
     */
    private String id;
    /**
     * Nombre del producto.
     */
    private String name;
    /**
     * Precio del producto.
     */
    private Number price;
    /**
     * Indica si el producto está disponible.
     */
    private Boolean availability;

}
