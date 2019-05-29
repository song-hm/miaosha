package com.shm.miaosha.service;

import com.shm.miaosha.dao.GoodsDao;
import com.shm.miaosha.domain.Goods;
import com.shm.miaosha.domain.MiaoshaGoods;
import com.shm.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: shm
 * @Date: 2019/5/29
 * @Description: com.shm.miaosha.service
 * @version: 1.0
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo(){
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);
    }
}
