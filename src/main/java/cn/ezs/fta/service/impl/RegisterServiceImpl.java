package cn.ezs.fta.service.impl;

import cn.ezs.fta.dao.UserDao;
import cn.ezs.fta.pojo.User;
import cn.ezs.fta.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author : 1998Gang
 * @date : 2020-08-05 11:00
 **/

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean register(User user) {
        return userDao.register(user);
    }
}
