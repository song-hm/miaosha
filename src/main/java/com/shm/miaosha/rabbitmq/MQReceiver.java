package com.shm.miaosha.rabbitmq;

import com.shm.miaosha.domain.MiaoshaOrder;
import com.shm.miaosha.domain.MiaoshaUser;
import com.shm.miaosha.domain.OrderInfo;
import com.shm.miaosha.redis.RedisService;
import com.shm.miaosha.result.CodeMsg;
import com.shm.miaosha.result.Result;
import com.shm.miaosha.service.GoodsService;
import com.shm.miaosha.service.MiaoshaService;
import com.shm.miaosha.service.OrderService;
import com.shm.miaosha.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: shm
 * @Date: 2019/6/3
 * @Description: com.shm.miaosha.rabbitmq
 * @version: 1.0
 */
@Service
public class MQReceiver {

    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoshaService miaoshaService;

    @Autowired
    private MQSender sender;

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receiver(String message){
        log.info("receiver message:"+message);
        MiaoshaMessage mm = RedisService.stringToBean(message, MiaoshaMessage.class);
        long goodsId = mm.getGoodsId();
        MiaoshaUser user = mm.getUser();

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        Integer stockCount = goods.getStockCount();
        if (stockCount <= 0){
            return;
        }

        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdOrderId(user.getId(),goodsId);
        if (order != null){
            return;
        }

        //减库存，下订单，写入秒杀订单
        OrderInfo orderInfo =miaoshaService.miaosha(user,goods);
    }

    /*
    @RabbitListener(queues = MQConfig.QUEUE)
    public void receiver(String message){
        log.info("receiver message:"+message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
    public void receiverTopic1(String message){
        log.info("topic receiver message1:"+message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
    public void receiverTopic2(String message){
        log.info("topic receiver message2:"+message);
    }

    @RabbitListener(queues = MQConfig.HEADERS_QUEUE)
    public void receiverHeaders(byte[] bytes){
        log.info("Headers receiver message2:"+new String(bytes));
    }
    */
}
