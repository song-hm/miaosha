package com.shm.miaosha.vo;

import com.shm.miaosha.domain.MiaoshaUser;
import lombok.Data;

/**
 * @Auther: shm
 * @Date: 2019/6/2
 * @Description: com.shm.miaosha.vo
 * @version: 1.0
 */
@Data
public class GoodsDetailVo {

    private int miaoshaStatus = 0;
    private int  remainSeconds = 0;
    private GoodsVo goods;
    private MiaoshaUser user;
}
