package com.shm.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: shm
 * @Date: 2019/5/28
 * @Description: com.shm.miaosha.domain
 * @version: 1.0
 */
@Data
public class MiaoshaUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
