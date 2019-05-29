package com.shm.miaosha.redis;

/**
 * @Auther: shm
 * @Date: 2019/5/26
 * @Description: com.shm.miaosha.redis
 * @version: 1.0
 */
public class UserKey extends BasePrefix{

    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");

}
