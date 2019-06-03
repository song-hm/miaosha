package com.shm.miaosha.redis;

/**
 * @Auther: shm
 * @Date: 2019/5/26
 * @Description: com.shm.miaosha.redis
 * @version: 1.0
 */
public class OrderKey extends BasePrefix{
    public OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey getOrderByUidGid = new OrderKey("moug");
}
