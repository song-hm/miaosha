package com.shm.miaosha.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: shm
 * @Date: 2019/5/28
 * @Description: com.shm.miaosha.util
 * @version: 1.0
 */
public class ValidatorUtil {
    public static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");
    public static boolean isMoboile(String src){
        if (StringUtils.isEmpty(src)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }

//    public static void main(String[] args){
//        System.out.println(isMoboile("12312341234"));
//        System.out.println(isMoboile("123123412"));
//    }

}
