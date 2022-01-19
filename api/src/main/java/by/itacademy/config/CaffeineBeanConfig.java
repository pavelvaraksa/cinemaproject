package by.itacademy.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

public class CaffeineBeanConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cinemaCacheManager = new CaffeineCacheManager("locations","cinemas","movies");
        cinemaCacheManager.setCaffeine(cacheProperties());
        return cinemaCacheManager;
    }

    public Caffeine<Object, Object> cacheProperties() {
        return Caffeine.newBuilder()
                .initialCapacity(10)
                .maximumSize(100)
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .weakKeys()
                .recordStats();
    }
}
