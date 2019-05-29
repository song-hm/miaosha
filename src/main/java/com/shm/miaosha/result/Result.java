package com.shm.miaosha.result;

import lombok.Getter;

/**
 * @Auther: shm
 * @Date: 2019/5/25
 * @Description: com.shm.miaosha.result
 * @version: 1.0
 */
@Getter
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if (cm == null){
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();

    }

    /**
     * 成功时的调用
     * @param data
     * @param <T> 定义泛型方法时，必须在返回值前边加一个<T>，来声明这是一个泛型方法，持有一个泛型T，然后才可以用泛型T作为方法的返回值。
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    /**
     * 失败时的调用
     * @param
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm){
        return new Result<T>(cm);
    }
}
