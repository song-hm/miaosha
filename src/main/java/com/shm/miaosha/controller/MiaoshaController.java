package com.shm.miaosha.controller;

import com.shm.miaosha.domain.MiaoshaOrder;
import com.shm.miaosha.domain.MiaoshaUser;
import com.shm.miaosha.domain.OrderInfo;
import com.shm.miaosha.redis.RedisService;
import com.shm.miaosha.result.CodeMsg;
import com.shm.miaosha.result.Result;
import com.shm.miaosha.service.GoodsService;
import com.shm.miaosha.service.MiaoshaService;
import com.shm.miaosha.service.MiaoshaUserService;
import com.shm.miaosha.service.OrderService;
import com.shm.miaosha.vo.GoodsVo;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: shm
 * @Date: 2019/5/29
 * @Description: com.shm.miaosha.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
    private static Logger log = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoshaService miaoshaService;

    //QPS 258.9  1000*10

    /**
     * GET  POST 有什么区别？
     * GET幂等 从服务端取数据
     * POST 对服务端数据进行更改
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/do_miaosha",method = RequestMethod.POST)
    @ResponseBody
    public Result<OrderInfo> miaosha(Model model, MiaoshaUser user,
                          @RequestParam("goodsId")long goodsId){
        model.addAttribute("user", user);
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        Integer stockCount = goods.getStockCount();
        if (stockCount <= 0){
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdOrderId(user.getId(),goodsId);
        if (order != null){
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }

        //减库存，下订单，写入秒杀订单
        OrderInfo orderInfo =miaoshaService.miaosha(user,goods);
        return Result.success(orderInfo);
    }
}
