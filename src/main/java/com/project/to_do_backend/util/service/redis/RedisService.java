package com.project.to_do_backend.util.service.redis;

import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Cache a set of string values in Redis under a specified key.
     *
     * @param key    The key under which the values will be cached in Redis.
     * @param values The set of string values to be cached.
     */
    public void cacheEnumValues(String key, Set<String> values) {
        // Use the Redis StringRedisTemplate to add the values to a Redis Set
        redisTemplate.opsForSet().add(key, values.toArray(new String[0]));
    }

    /**
     * Retrieve a set of string values from Redis based on a specified key.
     *
     * @param key The key used to retrieve the cached set of string values from
     *            Redis.
     * @return A Set containing the cached string values, or null if the key is not
     *         found in Redis.
     */
    public Set<String> getCachedEnumValues(String key) {
        // Use the Redis StringRedisTemplate to retrieve the members of a Redis Set
        return redisTemplate.opsForSet().members(key);
    }
}