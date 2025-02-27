package com.smo.orchestrator.application.response;

import lombok.Data;

@Data
public class AnswerData {
    private DataResponse dataResponse;

    public AnswerData(DataResponse dataResponse) {
        this.dataResponse = dataResponse;
    }
}
