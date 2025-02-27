package com.smo.orchestrator.domain.usecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smo.orchestrator.domain.models.response.DataResponseModel;
import com.smo.orchestrator.domain.ports.in.IGetSimilarProductsIdsUseCaseIn;
import com.smo.orchestrator.domain.ports.on.IGetSimilarProductsIdsUseCaseOn;
import com.smo.orchestrator.infrastructure.dataproviders.restclients.dto.Product;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GetSimilarProductsIdsUseCase implements IGetSimilarProductsIdsUseCaseIn {

    private final IGetSimilarProductsIdsUseCaseOn iGetSimilarProductsIdsUseCaseOn;
    private final ObjectMapper objectMapper;

    @Override
    public Mono<DataResponseModel> get(String productId) {
        return iGetSimilarProductsIdsUseCaseOn.getProducts(productId)
                .map(this::buildData)
                .collectList()
                .map(data -> DataResponseModel.builder().data(data).build());
    }

    private List<List<String>> buildData(List<Product> products) {
        List<String> productIds = new ArrayList<>();

        products.forEach(product -> productIds.add(product.getId()));

        List<List<String>> response = new ArrayList<>();
        response.add(productIds);

        return response;

    }
}
