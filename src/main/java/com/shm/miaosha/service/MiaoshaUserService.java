package com.shm.miaosha.service;

import com.shm.miaosha.dao.MiaoshaUserDao;
import com.shm.miaosha.domain.MiaoshaUser;
import com.shm.miaosha.exception.GlobalException;
import com.shm.miaosha.redis.MiaoshaUserKey;
import com.shm.miaosha.redis.RedisService;
import com.shm.miaosha.result.CodeMsg;
import com.shm.miaosha.util.MD5Util;
import com.shm.miaosha.util.UUIDUtil;
import com.shm.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: shm
 * @Date: 2019/5/28
 * @Description: com.shm.miaosha.service
 * @version: 1.0
 */
@Service
public class MiaoshaUserService {
    public static final String COOKIE_NAME_TOKEN = "token";
    @Autowired
    private MiaoshaUserDao miaoshaUserDao;

    @Autowired
    private RedisService redisService;

    public MiaoshaUser getById(Long id){
        //取缓存
        MiaoshaUser user = redisService.get(MiaoshaUserKey.getById, "" + id, MiaoshaUser.class);
        if (user != null){
            return user;
        }

        //取数据库

        user = miaoshaUserDao.getById(id);
        if (user != null){
            redisService.set(MiaoshaUserKey.getById,""+id,MiaoshaUser.class);
        }
        return user;
    }

    public Boolean updatePassword(String token,long id,String passwordNew){
        //查询user
        MiaoshaUser user = getById(id);
        if (user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        //先更新数据库
        MiaoshaUser toBeUpdate = new MiaoshaUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(passwordNew,user.getSalt()));
        miaoshaUserDao.update(toBeUpdate);

        //后处理缓存，注意顺序
        redisService.delete(MiaoshaUserKey.getById,""+id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(MiaoshaUserKey.token,token,user);
        return true;
    }


    public String login(HttpServletResponse response,LoginVo loginVo) {
        if (loginVo == null){
//            return CodeMsg.SERVER_ERROR;
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null){
//            return CodeMsg.MOBILE_NOT_EXIST;
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!dbPass.equals(calcPass)){
//            return CodeMsg.PASSWORD_ERROR;
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response,token,user);


        return token;

//        return CodeMsg.SUCCESS;
    }

    //生成cookie
    private void addCookie(HttpServletResponse response,String token,MiaoshaUser user){
        redisService.set(MiaoshaUserKey.token,token,user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    public MiaoshaUser getByToken(HttpServletResponse response,String token) {
        if (StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期
        if (user != null){
            addCookie(response,token,user);
        }
        return user;
    }
}
