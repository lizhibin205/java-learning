package com.example.cachedemo.common.service;

/**
 * redis的操作的接口方法
 */
public interface RedisService {
    boolean set(String key, String value);
    String get(String key);
    void expire(String key, long timeout);
}
