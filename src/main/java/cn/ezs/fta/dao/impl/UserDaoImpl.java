package cn.ezs.fta.dao.impl;

import cn.ezs.fta.dao.UserDao;
import cn.ezs.fta.mapper.RoleUserMapper;
import cn.ezs.fta.mapper.UserMapper;
import cn.ezs.fta.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * <h3>fat</h3>
 * <p></p>
 *
 * @author : 1998Gang
 * @date : 2020-08-05 15:30
 **/
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private RoleUserMapper roleUserMapper;

    /**
     * 代表普通用户
     */
    private final int ROLE_INDEX = 3;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(User user) {
        try {
            userMapper.insertUser(user);
            roleUserMapper.insertRoleUser(user.getUserName(), ROLE_INDEX);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            //手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

    }

    /**
     * 查询用户
     *
     * @return boolean
     */
    @Override
    public User selectByUserName(String userName) {
        return userMapper.selectByUsername(userName);
    }
}
