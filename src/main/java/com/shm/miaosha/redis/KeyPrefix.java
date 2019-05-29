package com.shm.miaosha.redis;

/**
 * @Auther: shm
 * @Date: 2019/5/26
 * @Description: com.shm.miaosha.redis
 * @version: 1.0
 */
public interface KeyPrefix {

    public int expireSeconds();
    public String getPrefix();
}
