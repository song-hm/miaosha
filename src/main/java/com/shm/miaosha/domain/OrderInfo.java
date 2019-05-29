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
public class OrderInfo {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long  deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;
}
