package com.smo.orchestrator.domain.usecase;

import com.smo.orchestrator.domain.models.response.DataResponseModel;
import com.smo.orchestrator.domain.ports.in.IGetDetailProductIdUseCaseIn;
import com.smo.orchestrator.domain.ports.on.IProductRestOn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Caso de uso para la obtención del detalle de un producto específico.
 *
 * <p>Implementa la interfaz {@link IGetDetailProductIdUseCaseIn} y actúa como
 * intermediario entre la capa de dominio y la capa de infraestructura, obteniendo
 * los datos desde {@link IProductRestOn}.</p>
 *
 * <p>Transforma los datos obtenidos en un objeto de tipo {@link DataResponseModel}
 * para ser utilizado en capas superiores.</p>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@RequiredArgsConstructor
@Component
public class GetDetailProductIdUseCase implements IGetDetailProductIdUseCaseIn {

    private final IProductRestOn iProductRestOn;

    /**
     * Obtiene los detalles de un producto basado en su identificador.
     *
     * @param productId identificador único del producto.
     * @return un {@link Mono} que emite el detalle del producto encapsulado en {@link DataResponseModel}.
     */
    @Override
    public Mono<Object> get(String productId) {
        return iProductRestOn.getProduct(productId)
                .map(data -> DataResponseModel.builder().data(data).build());
    }

}
