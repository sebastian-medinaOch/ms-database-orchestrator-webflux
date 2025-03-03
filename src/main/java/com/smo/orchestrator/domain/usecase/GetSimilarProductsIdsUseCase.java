package com.smo.orchestrator.domain.usecase;

import com.smo.orchestrator.domain.models.response.DataResponseModel;
import com.smo.orchestrator.domain.ports.in.IGetSimilarProductsIdsUseCaseIn;
import com.smo.orchestrator.domain.ports.on.IProductRestOn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * Caso de uso para la obtención de productos similares a un producto dado.
 *
 * <p>Implementa la interfaz {@link IGetSimilarProductsIdsUseCaseIn} y actúa como
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
public class GetSimilarProductsIdsUseCase implements IGetSimilarProductsIdsUseCaseIn {

    private final IProductRestOn iProductRestOn;

    /**
     * Obtiene los identificadores de productos similares a un producto dado.
     *
     * @param productId identificador único del producto base.
     * @return un {@link Flux} que emite los productos similares encapsulados en {@link DataResponseModel}.
     */
    @Override
    public Flux<DataResponseModel> get(String productId) {
        return iProductRestOn.getProducts(productId)
                .map(data -> DataResponseModel.builder().data(data).build());
    }

}
