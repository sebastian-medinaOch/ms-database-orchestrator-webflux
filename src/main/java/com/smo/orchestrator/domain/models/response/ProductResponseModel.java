package com.smo.orchestrator.domain.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseModel {

    private String id;
    private String name;
    private Number price;
    private Boolean availability;

}
