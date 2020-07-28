package com.xz.config;

import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CusCacheConfig {

/*    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() throws IOException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        return Redisson.create(config);
    }*/

    /**
     * 这里可以给每个类型的cache设置一个缓存时间,可以走配置文件
     */
    @Bean
    CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
        // 创建一个名称为"testMap"的缓存，过期时间ttl为24分钟，同时最长空闲时maxIdleTime为12分钟。
        //config.put("testMap", new CacheConfig(24 * 60 * 1000, 12 * 60 * 1000));
        config.put("ZJW_CLIENT_CACHE", new CacheConfig(60 * 1000, 0));
        config.put("ZJW_ACCESS_TOKEN_CACHE", new CacheConfig(60 * 1000, 0));

        RedissonSpringCacheCustomManager cacheManager = new RedissonSpringCacheCustomManager(redissonClient, config, JsonJacksonCodec.INSTANCE);
        cacheManager.setTtl(Duration.ofMinutes(3));
        return cacheManager;

        //return new RedissonSpringCacheManager(redissonClient, config, JsonJacksonCodec.INSTANCE);
    }
}