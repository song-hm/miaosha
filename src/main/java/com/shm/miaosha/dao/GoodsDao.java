package com.shm.miaosha.dao;

import com.shm.miaosha.domain.Goods;
import com.shm.miaosha.domain.MiaoshaGoods;
import com.shm.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Auther: shm
 * @Date: 2019/5/29
 * @Description: com.shm.miaosha.dao
 * @version: 1.0
 */
@Mapper
public interface GoodsDao {
    @Select("select g.*,mg.stock_count,mg.start_date,mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on g.id = mg.goods_id;")
    public List<GoodsVo> listGoodsVo();

    @Select("select g.*,mg.stock_count,mg.start_date,mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on g.id = mg.goods_id where goods_id = #{goodsId};")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    @Update("update miaosha_goods set stock_count = stock_count -1 where goods_id = #{goodsId};")
    public void reduceStock(MiaoshaGoods g);
}
