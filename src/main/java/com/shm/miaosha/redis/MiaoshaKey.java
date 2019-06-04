package com.shm.miaosha.redis;

/**
 * @Auther: shm
 * @Date: 2019/6/3
 * @Description: com.shm.miaosha.redis
 * @version: 1.0
 */

public class MiaoshaKey extends BasePrefix{
    public MiaoshaKey(String prefix) {
        super(prefix);
    }

    public static MiaoshaKey isGoodsOver = new MiaoshaKey("go");
}
