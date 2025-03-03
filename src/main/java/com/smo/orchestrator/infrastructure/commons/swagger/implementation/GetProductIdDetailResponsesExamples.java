package com.smo.orchestrator.infrastructure.commons.swagger.implementation;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GetProductIdDetailResponsesExamples {

    // 200 OK - Ejemplo de respuesta exitosa con datos
    public static final String SUCCESS_200_EXAMPLE = """
            {
                    "id": "string",
                    "name": "string",
                    "price": 0,
                    "availability": true
            }
            """;

    // 400 Bad Request
    public static final String ERROR_400_EXAMPLE = """
             {
                "status": "400",
                "message": "El header 'messageId' es requerido"
             }
            """;

    // 500 Internal Server Error
    public static final String ERROR_500_EXAMPLE = """
            {
                 "status": "500",
                 "message": "Ocurrio un error inesperado"
            }
            """;
}
