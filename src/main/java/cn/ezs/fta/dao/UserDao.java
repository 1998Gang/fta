package cn.ezs.fta.dao;

import cn.ezs.fta.pojo.User;

/**
 * @author 1998Gang
 */
public interface UserDao {
    /**
     * 注册操作
     * @param user user
     * @return
     */
    boolean register(User user);

    /**
     * 登录操作
     * @param userName username
     * @return boolean
     */
    User selectByUserName(String userName);
}
