package com.shm.miaosha.domain;

import lombok.Data;

/**
 * @Auther: shm
 * @Date: 2019/5/29
 * @Description: com.shm.miaosha.domain
 * @version: 1.0
 */
@Data
public class MiaoshaOrder {
    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;
}
