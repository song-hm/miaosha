package com.shm.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: shm
 * @Date: 2019/5/29
 * @Description: com.shm.miaosha.domain
 * @version: 1.0
 */
@Data
public class MiaoshaGoods {
    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
