package com.smo.orchestrator.infrastructure.commons.constants;

public class InfrastructureConstants {

    //Path
    public static final String PATH_PRODUCT_CONTROLLER = "/product";
    public static final String PATH_GET_DETAIL_PRODUCT_ID_CONTROLLER = "/{productId}";
    public static final String PATH_GET_SIMILAR_PRODUCTS_IDS_CONTROLLER = "/{productId}/similarids";

    //Paths ands headers
    public static final String PATH_VARIABLE_PRODUCT_ID = "productId";
    public static final String REQUEST_HEADER_MESSAGE_ID = "messageId";

    //MessageError
    public static final String MESSAGE_ERROR_PRODUCT_ID_EMPTY = "El parámetro 'productId' es requerido";
    public static final String MESSAGE_ERROR_MESSAGE_ID_EMPTY = "El header 'messageId' es requerido";
    public static final String MESSAGE_ERROR_GET_PRODUCT_NOT_FOUND = "No se encontró ningun regristro bajo ese productId";

    //Config
    public static final String CONFIG_BASE_URL_WEB_CLIENT_PRODUCT = "api.rest.product.url-base-path";
    public static final String CONFIG_URI_GET_PRODUCTS_WEB_CLIENT = "/product/{productId}/similarids";
    public static final String CONFIG_URI_GET_PRODUCT_WEB_CLIENT = "/product/{productId}";
    public static final String CONFIG_CACHE_PRODUCT = "product";
    public static final String CONFIG_CACHE_SIMILAR_PRODUCTS_IDS = "products";
    public static final String CONFIG_BEAN_CACHE = "cacheProduct";

    //Log
    public static final String LOG_EXCEPTION_BUSSINESS_EXCEPTION = "Error controlado: MessageId: {}, ErrorMessage: {}";
    public static final String LOG_EXCEPTION_ILLEGAL_ARGUMENT_EXCEPTION = "Parámetro inválido: MessageId: {}, ErrorMessage: {}";
    public static final String LOG_EXCEPTION_NO_RESOURCE_FOUND_EXCEPTION = "Error en la URL: MessageId: {}, ErrorMessage: {}";
    public static final String LOG_EXCEPTION_RESPONSE_STATUS_EXCEPTION = "Error HTTP: MessageId: {}, ErrorMessage: {}";
    public static final String LOG_EXCEPTION_EXCEPTION = "Error inesperado: MessageId: {}, ErrorMessage: {}";
    public static final String LOG_INFO_CONTROLLER_GET_DETAIL_PRODUCT_ID = "Se inicia el proceso para obtener el detalle del producto. MessageId: {} , ProductId: {}";
    public static final String LOG_INFO_CONTROLLER_GET_SIMILAR_PRODUCTS_IDS = "Se inicia el proceso para obtener los productos similares. MessageId: {} , ProductId: {}";
    public static final String LOG_INFO_CONTROLLER_GET_DETAIL_PRODUCT_ID_SUCCESS = "Se finalizó el proceso para obtener el detalle del producto. MessageId: {} , ProductId: {}";
    public static final String LOG_INFO_CONTROLLER_GET_SIMILAR_PRODUCTS_IDS_SUCCESS = "Se finalizó el proceso para obtener los productos similares. MessageId: {} , ProductId: {}";

    //Swagger constants
    public static final String SWAGGER_OPEN_API_GROUP = "Microservicio Orquestador de peticiones hacia la base de datos.";
    public static final String SWAGGER_OPEN_API_PATHS_TO_MATCH = "/**";
    public static final String SWAGGER_OPEN_API_INFO_TITLE = "Java 21 + Springboot 3.3.5 & OpenAPI";
    public static final String SWAGGER_OPEN_API_INFO_DESCRIPTION = "Servicio que permite la orquestación, escritura y lectura que se hace hacia la base de datos.";
    public static final String SWAGGER_OPEN_API_INFO_VERSION = "3.0.0";
    public static final String SWAGGER_OPEN_API_INFO_CONTACT_NAME = "Sebastian Medina Ochoa";
    public static final String SWAGGER_OPEN_API_INFO_CONTACT_URL = "https://github.com/sebastian-medinaOch";
    public static final String SWAGGER_OPEN_API_INFO_CONTACT_EMAIL = "sebasthyy1@gmail.com";
    public static final String SWAGGER_OPEN_API_COMPONENTS_INFO_TITLE = "Información de contacto";
    public static final String SWAGGER_OPEN_API_COMPONENTS_INFO_DESCRIPTION = "Servicio que permite la orquestación, escritura y lectura que se hace hacia la base de datos.";
    public static final String SWAGGER_PACKAGE_INFRASTRUCTURE_CONTROLLERS = "com.smo.orchestrator.infrastructure.controllers;";
    public static final String SWAGGER_GET_DETAIL_PRODUCT_ID = "Devuelve el detalle del producto para un productId determinado";
    public static final String SWAGGER_GET_SIMILAR_PRODUCTS_IDS = "Devuelve los productos similares a uno dado ordenados por similitud";
    public static final String SWAGGER_OPERATIONS_PRODUCTS_API_RESPONSE_200 = "Consulta generada correctamente";
    public static final String SWAGGER_API_RESPONSE_COMMON_API_400 = "El header 'messageId' es requerido";
    public static final String SWAGGER_API_RESPONSE_COMMON_API_500 = "Ocurrió un error inesperado";
    public static final String SWAGGER_ANSWER_EXCEPTION_DESCRIPTION = "Representa la estructura de la respuesta estándar del servicio cuando ocurre una exception";
    public static final String SWAGGER_ANSWER_DATA_DESCRIPTION = "Representa la estructura de la respuesta estándar del servicio";
    public static final String SWAGGER_ANSWER_EXCEPTION_STATUS_DESCRIPTION = "Código de estado de la respuesta.";
    public static final String SWAGGER_ANSWER_EXCEPTION_MESSAGE_DESCRIPTION = "Mensaje de la respuesta.";
    public static final String SWAGGER_DATA_RESPONSE_DESCRIPTION = "Representa la sub estructura que contiene los datos de la respuesta.";
    public static final String SWAGGER_DATA_RESPONSE_EXAMPLE = "[[1,2,3]]";
    public static final String SWAGGER_OBJECT_DESCRIPTION = "Representa la estructura que contiene los datos de la respuesta.";
    public static final String SWAGGER_OBJECT_EXAMPLE = "[1,2,3]";

    //Utility constants
    public static final String UTILITY_CODE_STATUS_200 = "200";
    public static final String UTILITY_CODE_STATUS_400 = "400";
    public static final String UTILITY_CODE_STATUS_500 = "500";
}
