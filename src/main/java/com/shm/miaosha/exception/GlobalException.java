package com.shm.miaosha.exception;

import com.shm.miaosha.result.CodeMsg;
import lombok.Getter;

/**
 * @Auther: shm
 * @Date: 2019/5/28
 * @Description: com.shm.miaosha.exception
 * @version: 1.0
 */
@Getter
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = -6362789657343557195L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm){
        super(cm.toString());
        this.cm = cm;
    }
}
