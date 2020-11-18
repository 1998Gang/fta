package cn.ezs.fta.mapper;

import cn.ezs.fta.pojo.User;
import org.apache.ibatis.annotations.Param;


/**
 * 用户操作接口
 * @author 1998Gang
 *
 */

public interface UserMapper {

    /**
     * 注册操作
     * @param user
     */
    void insertUser(User user);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User selectByUsername(@Param("userName") String username);
}
