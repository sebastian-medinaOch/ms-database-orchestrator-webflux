package com.smo.orchestrator.application.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smo.orchestrator.application.response.DataResponse;
import com.smo.orchestrator.application.services.interfaces.IGetDetailProductId;
import com.smo.orchestrator.domain.ports.in.IGetDetailProductIdUseCaseIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Servicio que gestiona la obtención del detalle de un producto específico.
 *
 * <p>Implementa la interfaz {@link IGetDetailProductId} y actúa como intermediario
 * entre la capa de aplicación y el caso de uso definido en {@link IGetDetailProductIdUseCaseIn}.</p>
 *
 * <p>Convierte la respuesta obtenida en un formato adecuado utilizando {@link ObjectMapper}.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@RequiredArgsConstructor
@Service
public class GetDetailProductIdService implements IGetDetailProductId {

    private final IGetDetailProductIdUseCaseIn iGetDetailProductIdUseCaseIn;
    private final ObjectMapper objectMapper;

    /**
     * Obtiene los detalles de un producto basado en su identificador.
     *
     * @param productId identificador único del producto.
     * @return un {@link Mono} que emite el objeto con los detalles del producto.
     */
    @Override
    public Mono<Object> get(String productId) {
        return iGetDetailProductIdUseCaseIn.get(productId)
                .map(products -> objectMapper.convertValue(products, DataResponse.class).getData());
    }

}
