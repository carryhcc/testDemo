package com.example.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 缓存配置
 *
 * @author : cchu
 * Date: 2023/5/8 09:32
 */
@Configuration
@EnableCaching
public class MyCacheConfig {
    @Bean
    public Caffeine caffeineConfig() {
        return
                Caffeine.newBuilder()
                        .maximumSize(10000).
                        expireAfterWrite(60, TimeUnit.MINUTES);
    }
    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}