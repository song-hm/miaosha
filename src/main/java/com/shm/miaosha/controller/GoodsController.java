package com.shm.miaosha.controller;

import com.shm.miaosha.domain.MiaoshaUser;
import com.shm.miaosha.redis.GoodsKey;
import com.shm.miaosha.redis.RedisService;
import com.shm.miaosha.result.Result;
import com.shm.miaosha.service.GoodsService;
import com.shm.miaosha.service.MiaoshaUserService;
import com.shm.miaosha.vo.GoodsDetailVo;
import com.shm.miaosha.vo.GoodsVo;
import com.shm.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: shm
 * @Date: 2019/5/23
 * @Description: com.shm.miaosha.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    private static Logger log = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    /**
     * QPS 243.5  1000*10
     * QPS 323
     **/
    @RequestMapping(value = "/to_list",produces = "text/html")
    @ResponseBody
    public String toList(Model model, MiaoshaUser user, HttpServletRequest request,
                         HttpServletResponse response){
        model.addAttribute("user", user);

        //取缓存 页面缓存
        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if (!StringUtils.isEmpty(html)){
            return html;
        }
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList",goodsList);
//        return "goods_list";

        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        //手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", context);
        if (!StringUtils.isEmpty(html)){
            redisService.set(GoodsKey.getGoodsList,"",html);
        }
        return html;

    }

    @RequestMapping(value = "/to_detail2/{goodsId}",produces = "text/html")
    @ResponseBody
    public String detail2(Model model, MiaoshaUser user, @PathVariable("goodsId")long goodsId,
                         HttpServletRequest request,HttpServletResponse response){
        model.addAttribute("user",user);

        //取缓存 URL缓存
        String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);
        if (!StringUtils.isEmpty(html)){
            return html;
        }

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods",goods);


        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int  remainSeconds = 0;
        if (now < startAt){//秒杀未开始，倒计时
            miaoshaStatus =0;
            remainSeconds = (int)((startAt-now)/1000);
        }else if (now > endAt){//秒杀结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else{//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSeconds",remainSeconds);
//        return "goods_detail";

        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        //手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", context);
        if (!StringUtils.isEmpty(html)){
            redisService.set(GoodsKey.getGoodsDetail,""+goodsId,html);
        }
        return html;
    }


    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(Model model, MiaoshaUser user, @PathVariable("goodsId")long goodsId,
                                        HttpServletRequest request, HttpServletResponse response){

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int  remainSeconds = 0;
        if (now < startAt){//秒杀未开始，倒计时
            miaoshaStatus =0;
            remainSeconds = (int)((startAt-now)/1000);
        }else if (now > endAt){//秒杀结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else{//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }

        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
        goodsDetailVo.setGoods(goods);
        goodsDetailVo.setMiaoshaStatus(miaoshaStatus);
        goodsDetailVo.setRemainSeconds(remainSeconds);
        goodsDetailVo.setUser(user);
        return Result.success(goodsDetailVo);
    }

}
