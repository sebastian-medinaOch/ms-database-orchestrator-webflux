package com.smo.orchestrator.infrastructure.commons.constants;

public class InfrastructureConstants {

    //Path
    public static final String PATH_PRODUCT_CONTROLLER = "/product";
    public static final String PATH_GET_DETAIL_PRODUCT_ID_CONTROLLER = "/{productId}";
    public static final String PATH_GET_SIMILAR_PRODUCTS_IDS_CONTROLLER = "/{productId}/similarids";

    //Paths ands headers
    public static final String PATH_VARIABLE_PRODUCT_ID = "productId";
    public static final String REQUEST_HEADER_PRODUCT_ID = "messageId";

    //MessageError
    public static final String MESSAGE_ERROR_PRODUCT_ID_EMPTY = "El parámetro 'productId' es requerido";
    public static final String MESSAGE_ERROR_MESSAGE_ID_EMPTY = "El header 'messageId' es requerido";
    public static final String MESSAGE_ERROR_EXCEPTION = "Error interno en el servidor";
    public static final String MESSAGE_ERROR_GET_PRODUCT_NOT_FOUND = "No se encontró ningun regristro bajo ese productId";

    //Config
    public static final String CONFIG_BASE_URL_WEB_CLIENT_PRODUCT = "http://localhost:3001";
    public static final String CONFIG_URI_GET_PRODUCTS_WEB_CLIENT = "/product/{productId}/similarids";
    public static final String CONFIG_URI_GET_PRODUCT_WEB_CLIENT = "/product/{productId}";

    //Log
    public static final String LOG_EXCEPTION_BUSSINESS_EXCEPTION = "Error controlado: {}";
    public static final String LOG_EXCEPTION_ILLEGAL_ARGUMENT_EXCEPTION = "Parámetro inválido: {}";
    public static final String LOG_EXCEPTION_NO_RESOURCE_FOUND_EXCEPTION = "Error en la URL: {}";
    public static final String LOG_EXCEPTION_RESPONSE_STATUS_EXCEPTION = "Error HTTP: {}";
    public static final String LOG_EXCEPTION_EXCEPTION = "Error inesperado: {}";


}
