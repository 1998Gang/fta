package cn.ezs.fta.service;

import cn.ezs.fta.pojo.User;

/**
 *  注册service
 * @author 1998Gang
 */
public interface RegisterService {
    /**
     * 注册
     * @param user user
     * @return boolean
     */
    boolean register(User user);
}
