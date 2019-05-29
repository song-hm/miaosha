package com.shm.miaosha.service;

import com.shm.miaosha.domain.Goods;
import com.shm.miaosha.domain.MiaoshaUser;
import com.shm.miaosha.domain.OrderInfo;
import com.shm.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: shm
 * @Date: 2019/5/29
 * @Description: com.shm.miaosha.service
 * @version: 1.0
 */
@Service
public class MiaoshaService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存，下订单，写入秒杀订单
        goodsService.reduceStock(goods);

        //order_info miaosha_order
        return orderService.createOrder(user,goods);
    }
}
