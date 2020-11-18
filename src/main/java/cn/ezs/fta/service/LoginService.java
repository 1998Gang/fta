package cn.ezs.fta.service;

import cn.ezs.fta.pojo.User;

/**
 * 登录
 * @author 1998Gang
 */
public interface LoginService {
    /**
     * 登录
     * @return
     * @param user
     */
    String login(User user);
}
