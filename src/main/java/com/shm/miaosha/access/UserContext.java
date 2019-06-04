package com.shm.miaosha.access;

import com.shm.miaosha.domain.MiaoshaUser;

/**
 * @Auther: shm
 * @Date: 2019/6/4
 * @Description: com.shm.miaosha.access
 * @version: 1.0
 */

public class UserContext {

    //线程绑定、线程安全的
    private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<>();

    public static void setUser(MiaoshaUser user){
        userHolder.set(user);
    }

    public static MiaoshaUser getUser(){
        return userHolder.get();
    }

}
