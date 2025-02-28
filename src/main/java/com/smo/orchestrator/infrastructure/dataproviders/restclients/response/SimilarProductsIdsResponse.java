package com.smo.orchestrator.infrastructure.dataproviders.restclients.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimilarProductsIdsResponse {

    private List<ArrayList<String>> listIds;

}
