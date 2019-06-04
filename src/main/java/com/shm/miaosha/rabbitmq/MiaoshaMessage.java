package com.shm.miaosha.rabbitmq;

import com.shm.miaosha.domain.MiaoshaUser;
import lombok.Data;

/**
 * @Auther: shm
 * @Date: 2019/6/3
 * @Description: com.shm.miaosha.rabbitmq
 * @version: 1.0
 */
@Data
public class MiaoshaMessage {
    private MiaoshaUser user;
    private long goodsId;
}
