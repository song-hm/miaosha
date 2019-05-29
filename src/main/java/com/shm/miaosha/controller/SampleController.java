package com.shm.miaosha.controller;

import com.shm.miaosha.domain.User;
import com.shm.miaosha.redis.RedisService;
import com.shm.miaosha.redis.UserKey;
import com.shm.miaosha.result.CodeMsg;
import com.shm.miaosha.result.Result;
import com.shm.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: shm
 * @Date: 2019/5/23
 * @Description: com.shm.miaosha.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","admin");
        return "hello";
    }

    //1.rest api json数据输出 2.页面
    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello(){
        return Result.success("hello!");
//        return new Result(0,"success","hello!");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError(){
        return Result.error(CodeMsg.SERVER_ERROR);
//        return new Result(500100,"error!");
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet(){
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx(){
        boolean tx = userService.tx();
        return Result.success(tx);
    }


    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
        User key1 = redisService.get(UserKey.getById,""+1, User.class);
        return Result.success(key1);
    }
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){
        User user = new User();
        user.setId(1);
        user.setName("11111");
        boolean key2 = redisService.set(UserKey.getById,""+1, user);
        return Result.success(true);
    }
}
