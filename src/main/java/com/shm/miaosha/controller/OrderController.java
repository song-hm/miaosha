package com.shm.miaosha.controller;

import com.shm.miaosha.domain.MiaoshaUser;
import com.shm.miaosha.domain.OrderInfo;
import com.shm.miaosha.result.CodeMsg;
import com.shm.miaosha.result.Result;
import com.shm.miaosha.service.GoodsService;
import com.shm.miaosha.service.OrderService;
import com.shm.miaosha.vo.GoodsVo;
import com.shm.miaosha.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Request;

/**
 * @Auther: shm
 * @Date: 2019/6/3
 * @Description: com.shm.miaosha.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> detail(Model model, MiaoshaUser user,
                                        @RequestParam("orderId") long orderId){
        if (user == null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if (order == null){
            return Result.error(CodeMsg.ORDER_NO_EXIST);
        }
        Long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setGoods(goods);
        vo.setOrder(order);
        return Result.success(vo);
    }
}
