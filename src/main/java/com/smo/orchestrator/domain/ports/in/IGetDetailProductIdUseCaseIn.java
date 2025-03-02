package com.smo.orchestrator.domain.ports.in;

import reactor.core.publisher.Mono;

public interface IGetDetailProductIdUseCaseIn {

    Mono<Object> get(String productId);

}
