package com.smo.orchestrator.application.services.interfaces;

import reactor.core.publisher.Mono;

public interface IGetDetailProductId {

    Mono<Object> get(String productId);

}
