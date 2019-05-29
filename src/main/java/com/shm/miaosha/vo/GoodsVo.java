package com.shm.miaosha.vo;

import com.shm.miaosha.domain.Goods;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: shm
 * @Date: 2019/5/29
 * @Description: com.shm.miaosha.vo
 * @version: 1.0
 */
@Data
public class GoodsVo extends Goods {
    private double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
