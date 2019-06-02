package com.shm.miaosha.controller;

import com.shm.miaosha.domain.MiaoshaUser;
import com.shm.miaosha.redis.RedisService;
import com.shm.miaosha.result.Result;
import com.shm.miaosha.service.MiaoshaUserService;
import com.shm.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: shm
 * @Date: 2019/5/23
 * @Description: com.shm.miaosha.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(MiaoshaUser user, Model model){
        return Result.success(user);
    }

}
