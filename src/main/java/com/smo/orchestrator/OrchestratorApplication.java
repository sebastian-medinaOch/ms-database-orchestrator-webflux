package com.smo.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Clase principal de la aplicación Orchestrator.
 *
 * <p>Esta clase inicializa y ejecuta la aplicación Spring Boot. Está anotada con
 * {@link SpringBootApplication}, lo que la convierte en el punto de entrada principal.</p>
 *
 * <p>Adicionalmente, se habilita la funcionalidad de caché con {@link EnableCaching}.</p>
 *
 * @author ...
 * @since 1.0
 */
@SpringBootApplication
@EnableCaching
public class OrchestratorApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot.
	 *
	 * @param args argumentos de línea de comandos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(OrchestratorApplication.class, args);
	}

}
