package com.shm.miaosha.service;

import com.shm.miaosha.dao.OrderDao;
import com.shm.miaosha.domain.MiaoshaOrder;
import com.shm.miaosha.domain.MiaoshaUser;
import com.shm.miaosha.domain.OrderInfo;
import com.shm.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Auther: shm
 * @Date: 2019/5/29
 * @Description: com.shm.miaosha.service
 * @version: 1.0
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public MiaoshaOrder getMiaoshaOrderByUserIdOrderId(long userId, long goodsId) {
        return orderDao.getMiaoshaOrderByUserIdOrderId(userId,goodsId);
    }

    @Transactional
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setStatus(0);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setUserId(user.getId());
        orderInfo.setGoodsPrice(goods.getGoodsPrice());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setOrderChannel(1);

        long orderId = orderDao.insert(orderInfo);

        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setUserId(user.getId());
        miaoshaOrder.setOrderId(orderId);
        orderDao.insertMiaoshaOrder(miaoshaOrder);

        return orderInfo;
    }
}
