|   Fecha    |      Descripción       |     Realizado por      |
|:----------:|:----------------------:|:----------------------:|
| 27/02/2025 | Creación del Documento | Sebastian Medina Ochoa |

# Documentación microservicio

## 1. Información general

### 1.1 Nombre de la API

* **Nombre:** ms-database-orchestrator-webflux
* **Clasificación del servicio:** Orquestador de peticiones hacia la base de datos.
* **Versión actual:** v1.0.0
* **Propietario:** Sebastian Medina Ochoa

### 1.2 Propósito de la API

* **Descripción:** Servicio que permite la orquestación, escritura y lectura que se hace hacia la base de datos.
* **Objetivos del servicio:** Poder realizar orquestación, escritura y lectura de cualquier tabla de la base de datos.
* **Usuarios Destinatarios:** Servicios que requieran realizar escritura o lectura de la base de datos.

### 1.3 Detalles de Campos en la Petición y Respuesta
Esta sección describe los campos que se utilizan en las peticiones y respuestas de la API.

### Headers

| Nombre del Campo | Descripción                      | Tipo de Dato | Dato Obligatorio | Valores Permitidos | Observaciones                                                                                                                 |
|:-----------------|:---------------------------------|:-------------|:-----------------|:-------------------|:------------------------------------------------------------------------------------------------------------------------------|
| `messageId`      | Identificador unico del flujo.   | String       | SI               | VALORES UUID       | La idea es que cada petición tenga un messageId distinto para poder indentificarlo en los logs en el soporte de la aplicación |

### Path Variable

| Nombre del Campo | Descripción                         | Tipo de Dato | Dato Obligatorio | Valores Permitidos | Observaciones                                                              |
|:-----------------|:------------------------------------|:-------------|:-----------------|:-------------------|:---------------------------------------------------------------------------|
| `productId`      | Identificador de un producto unico. | String       | SI               | VALORES UUID       | Este idenfiticador unico permitirá recuperar el/los producto/s a consultar |

## 2. Endpoints

### 2.1 Lista de Endpoints

| Método   | Endpoint                            | Descripción                                                       | 
|:---------|:------------------------------------|:------------------------------------------------------------------|
| `GET`    | "/product/{productId}/similarids"   | Returns the similar products to a given one ordered by similarity |
| `GET`    | "/product/{productId}"              | Returns the product detail for a given productId                  |


### 2.2 Detalles del Endpoint

#### **GET /product/{productId}/similarids**

* **Descripción:** "Returns the similar products to a given one ordered by similarity"
* **Autenticación Requerida:** "No"
* * **Cuerpo de la Solicitud (Request Body):**
      ```json lines
            curl --location 'http://localhost:5000/product/{productId}/similarids' --header 'messageId: 123'
      ```

* **Respuestas:**
* * **Código 200:** "Ok":
     ```json lines
        [
            [
                "1",
                "2",
                "3"
            ]
        ]
    ```
* * **Código 400:** "Bad Request":
    ```json lines
       {
           "error": "BadRequest",
           "message": "productId cannot be empty"
       }
    ```       

* * **Código 404:** "Not Found":
     ```json lines
       {
            "error": "NotFound",
            "message": "Similar Products Not Found"
        }
     ```

#### **GET /product/{productId}**

* **Descripción:** "Returns the product detail for a given productId"
* **Autenticación Requerida:** "No"
* * **Cuerpo de la Solicitud (Request Body):**
      ```json lines
            curl --location 'http://localhost:5000/product/{productId}' --header 'messageId: 123'
      ```

* **Respuestas:**
* * **Código 200:** "Ok":
     ```json lines
        {
            "id": "string",
            "name": "string",
            "price": 0,
            "availability": true
        }
    ```
    
* * **Código 400:** "Bad Request":
    ```json lines
       {
           "error": "BadRequest",
           "message": "productId cannot be empty"
       }
    ```   
    
     ```
* * **Código 404:** "Not Found":
     ```json lines
        {
            "error": "NotFound",
            "message": "Product Not Found"
        }
     ```

---

### 2.3 Validación de Datos
Para asegurar la integridad de los datos recibidos y enviados a través de las APIs, se deben realizar validaciones en todos los campos de las solicitudes y respuestas. A continuación se detallan algunos ejemplos de validaciones:

1. **Campo `productId`:**
* **Tipo de Dato:** cadena `String`.
* **Validación:** No debe ser un valor nulo ni estar vacío.

2. **Campo `messageId`:**
* **Tipo de Dato:** cadena `String`.
* **Validación:** No debe ser un valor nulo ni estar vacío.

## 3. Contacto y Soporte

### 3.1. Información de Contacto

* **Email de Soporte:** "sebasthyy1@gmail.com"

#### Acceso a la Colección

Puedes acceder a la colección de Postman utilizando el siguiente enlace:

[https://drive.google.com/drive/folders/1MT9f2KIoiqiCcl8TS_Igbrk4-_WgvFUS?usp=sharing](Link)

## 3. Correr el Microservicio

#### Construir el microservicio con gradle: Comando: gradle build
#### Levantar los contenedores definidos en el docker-compose.yml. Comando: docker-compose up -d simulado influxdb grafana -> este comando se tiene que ejecutar en este respositorio ya que necesita del docker-compose.yml
#### Construir la imagen del microservicio docker build -t ms-database-orchestrator-webflux .  -> este comando se tiene que ejecutar en este respositorio ya que necesita del Dockerfile
#### Correr la imagen del microservicio docker run -d -p 5000:5000 --name ms-database-orchestrator-webflux-container ms-database-orchestrator-webflux
#### Correr la pruebas con k6. Comando: docker-compose run --rm k6 run scripts/test.js
