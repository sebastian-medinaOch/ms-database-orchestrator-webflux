package com.smo.orchestrator.application.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smo.orchestrator.application.response.DataResponse;
import com.smo.orchestrator.application.services.interfaces.IGetSimilarProductsIds;
import com.smo.orchestrator.domain.ports.in.IGetSimilarProductsIdsUseCaseIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Servicio que gestiona la obtención de productos similares a uno dado.
 *
 * <p>Implementa la interfaz {@link IGetSimilarProductsIds} y actúa como intermediario
 * entre la capa de aplicación y el caso de uso definido en {@link IGetSimilarProductsIdsUseCaseIn}.</p>
 *
 * <p>Convierte la respuesta obtenida en un formato adecuado utilizando {@link ObjectMapper}.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@RequiredArgsConstructor
@Service
public class GetSimilarProductsIdsService implements IGetSimilarProductsIds {

    private final IGetSimilarProductsIdsUseCaseIn iGetSimilarProductsIdsUseCaseIn;
    private final ObjectMapper objectMapper;

    /**
     * Obtiene los identificadores de productos similares a un producto dado.
     *
     * @param productId identificador único del producto base.
     * @return un {@link Flux} que emite los IDs de los productos similares.
     */
    @Override
    public Flux<Object> get(String productId) {
        return iGetSimilarProductsIdsUseCaseIn.get(productId)
                .map(products -> objectMapper.convertValue(products, DataResponse.class).getData());
    }

}
