package com.shm.miaosha.redis;

/**
 * @Auther: shm
 * @Date: 2019/6/3
 * @Description: com.shm.miaosha.redis
 * @version: 1.0
 */

public class AccessKey extends BasePrefix{
    public AccessKey(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }

    public static AccessKey access = new AccessKey(5,"access");
}
