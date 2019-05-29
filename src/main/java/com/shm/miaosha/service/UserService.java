package com.shm.miaosha.service;

import com.shm.miaosha.dao.UserDao;
import com.shm.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: shm
 * @Date: 2019/5/25
 * @Description: com.shm.miaosha.service
 * @version: 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User getById(int id){
        return userDao.getById(id);
    }

    @Transactional
    public boolean tx(){
        User user1 = new User();
        user1.setId(2);
        user1.setName("user1");
        userDao.insert(user1);

        User user2 = new User();
        user2.setId(1);
        user2.setName("user2");
        userDao.insert(user2);
        return true;
    }

}
