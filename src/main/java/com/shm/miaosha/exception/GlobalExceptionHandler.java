package com.shm.miaosha.exception;

import com.shm.miaosha.result.CodeMsg;
import com.shm.miaosha.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: shm
 * @Date: 2019/5/28
 * @Description: com.shm.miaosha.exception
 * @version: 1.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
        if (e instanceof GlobalException){
            GlobalException ge = (GlobalException)e;
            return Result.error(ge.getCm());

        }else if (e instanceof BindException){
            BindException ex = (BindException)e;
            List<ObjectError> allErrors = ex.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            String defaultMessage = objectError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(defaultMessage));
        }else{
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

}
