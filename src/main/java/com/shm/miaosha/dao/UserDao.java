package com.shm.miaosha.dao;

import com.shm.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: shm
 * @Date: 2019/5/25
 * @Description: com.shm.miaosha.dao
 * @version: 1.0
 */
@Mapper
public interface UserDao {
    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") int id);

    @Insert("insert into user(id,name) values(#{id},#{name})")
    public int insert(User user);
}
