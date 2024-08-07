package ir.snapp.pay.side.project.vamak.core.cfg;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class CacheConfig {

    @Bean
    public CacheManagerCustomizer<ConcurrentMapCacheManager> concurrentMapCacheManagerCacheManagerCustomizer() {
        return m -> m.setAllowNullValues(false);
    }
}
