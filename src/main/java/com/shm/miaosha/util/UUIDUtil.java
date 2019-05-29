package com.shm.miaosha.util;

import java.util.UUID;

/**
 * @Auther: shm
 * @Date: 2019/5/28
 * @Description: com.shm.miaosha.util
 * @version: 1.0
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
