package com.shm.miaosha.validator;

import com.shm.miaosha.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * @Auther: shm
 * @Date: 2019/5/28
 * @Description: com.shm.miaosha.validator
 * @version: 1.0
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return ValidatorUtil.isMoboile(s);
        }else {
            if (StringUtils.isEmpty(s)) {
                return true;
            }else {
               return ValidatorUtil.isMoboile(s);
            }
        }
    }
}
