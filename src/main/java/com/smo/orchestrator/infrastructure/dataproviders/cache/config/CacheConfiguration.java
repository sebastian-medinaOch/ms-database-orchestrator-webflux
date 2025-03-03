package com.smo.orchestrator.infrastructure.dataproviders.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import static com.smo.orchestrator.infrastructure.commons.constants.InfrastructureConstants.CONFIG_BEAN_CACHE;
/**
 * Configuración de la caché utilizando Caffeine en la aplicación.
 *
 * <p>Esta clase define la configuración para el sistema de caché basado en Caffeine,
 * que permite almacenar en memoria objetos y mejorar el rendimiento al reducir la carga en las fuentes de datos.</p>
 *
 * <p>Está anotada con {@code @Configuration} para ser detectada como un componente de configuración de Spring,
 * y con {@code @EnableCaching} para habilitar el soporte de caché en la aplicación.</p>
 *
 * <p>Utiliza {@link CaffeineCacheManager} para gestionar la caché con las siguientes configuraciones:</p>
 * <ul>
 *     <li>Expiración de elementos después de 10 minutos sin uso.</li>
 *     <li>Límite máximo de 100 elementos en caché.</li>
 *     <li>Modo de caché asíncrono activado.</li>
 * </ul>
 *
 * @author Sebastian Medina Ochoa
 * @since 1.0
 */
@Data
@Configuration
@EnableCaching
@Log4j2
public class CacheConfiguration {

    /**
     * Crea y configura un bean de {@link CaffeineCacheManager} con las políticas de caché definidas.
     *
     * <p>La caché tiene un tiempo de expiración de 10 minutos desde la última escritura
     * y un tamaño máximo de 100 elementos.</p>
     *
     * @return una instancia de {@link CaffeineCacheManager} configurada.
     */
    @Bean(name = CONFIG_BEAN_CACHE)
    public CaffeineCacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(100));
        cacheManager.setAsyncCacheMode(true);
        return cacheManager;
    }

}
