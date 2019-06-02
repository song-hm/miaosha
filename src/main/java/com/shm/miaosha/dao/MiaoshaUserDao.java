package com.shm.miaosha.dao;

import com.shm.miaosha.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Auther: shm
 * @Date: 2019/5/28
 * @Description: com.shm.miaosha.dao
 * @version: 1.0
 */

@Mapper
public interface MiaoshaUserDao {
    @Select("select * from miaosha_user where id = #{id}")
    public MiaoshaUser getById(@Param("id") long id);

    @Update("update miaosha_user set password = #{pasword} where id = #{id}")
    public void update(MiaoshaUser toBeUpdate);
}
