package com.xz.config;

import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;

import java.time.Duration;
import java.util.Map;

public class RedissonSpringCacheCustomManager extends RedissonSpringCacheManager {
    /**
     * cache global ttl
     */
    private Duration ttl = Duration.ZERO;

    public RedissonSpringCacheCustomManager(RedissonClient redisson) {
        super(redisson, (String) null, null);
    }

    public RedissonSpringCacheCustomManager(RedissonClient redisson, Map<String, ? extends CacheConfig> config) {
        super(redisson, config, null);
    }

    public RedissonSpringCacheCustomManager(RedissonClient redisson, Map<String, ? extends CacheConfig> config, Codec codec) {
        super(redisson, config, codec);
    }

    public RedissonSpringCacheCustomManager(RedissonClient redisson, String configLocation) {
        super(redisson, configLocation, null);
    }

    public RedissonSpringCacheCustomManager(RedissonClient redisson, String configLocation, Codec codec) {
        super(redisson, configLocation, codec);
    }

    public Duration getTtl() {
        return ttl;
    }

    public void setTtl(Duration ttl) {
        this.ttl = ttl;
    }

    @Override
    public CacheConfig createDefaultConfig() {
        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setTTL(ttl.toMillis());
        return cacheConfig;
    }
}
