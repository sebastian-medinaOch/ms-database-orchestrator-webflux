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

@Data
@Configuration
@EnableCaching
@Log4j2
public class CacheConfiguration {

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
