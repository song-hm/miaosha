package com.shm.miaosha.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: shm
 * @Date: 2019/5/26
 * @Description: com.shm.miaosha.redis
 * @version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisConfig {
    private String host;
    private int port;
    private String password;
    private int timeout;//秒
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;
}
