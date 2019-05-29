package com.shm.miaosha.controller;

import com.shm.miaosha.domain.MiaoshaUser;
import com.shm.miaosha.redis.RedisService;
import com.shm.miaosha.result.Result;
import com.shm.miaosha.service.MiaoshaUserService;
import com.shm.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: shm
 * @Date: 2019/5/23
 * @Description: com.shm.miaosha.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    private static Logger log = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/to_list")
    public String toList(Model model,MiaoshaUser user
//            HttpServletResponse response,
//                         @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false)String cookieToken,
//                         @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false)String paramToken,
                         ){
//        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
//            return "login";
//        }
//        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
//        MiaoshaUser user = miaoshaUserService.getByToken(response,token);

        if (user != null) {
            model.addAttribute("user", user);
        }
        return "goods_list";
    }

    @RequestMapping("/to_detail")
    public String detail(HttpServletResponse response,Model model,
                         @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false)String cookieToken,
                         @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false)String paramToken){
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
            return "login";
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        MiaoshaUser user = miaoshaUserService.getByToken(response,token);
        model.addAttribute("user",user);
        return "goods_list";
    }

}
