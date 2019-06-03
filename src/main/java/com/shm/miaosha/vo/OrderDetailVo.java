package com.shm.miaosha.vo;

import com.shm.miaosha.domain.OrderInfo;
import lombok.Data;

/**
 * @Auther: shm
 * @Date: 2019/6/3
 * @Description: com.shm.miaosha.vo
 * @version: 1.0
 */
@Data
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;
}
