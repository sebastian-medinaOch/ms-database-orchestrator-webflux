package com.smo.orchestrator.domain.ports.on;

import com.smo.orchestrator.infrastructure.dataproviders.restclients.dto.Product;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IGetSimilarProductsIdsUseCaseOn {

    Flux<List<Product>> getProducts(String productId);

}
