package cn.ezs.fta.service.impl;

import cn.ezs.fta.dao.UserDao;
import cn.ezs.fta.mapper.RoleUserMapper;
import cn.ezs.fta.pojo.LoginMassage;
import cn.ezs.fta.pojo.User;
import cn.ezs.fta.service.LoginService;
import cn.ezs.fta.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**

 *
 * @author : 1998Gang
 * @date : 2020-08-05 19:03
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired(required = false)
    private RoleUserMapper roleUserMapper;


    /**
     * 登录
     *
     * @return token
     * @param user user
     */
    @Override
    public String login(User user) {

        String userName=user.getUserName();
        //查询用户数据
        User user1 = userDao.selectByUserName(userName);
        //校验 密码
        if (user1!=null&&user.getPassword().equals(user1.getPassword())){
            //身份校验成功，查询用户角色
            LoginMassage loginMassage = roleUserMapper.selectByUsername(userName);
            //生成token并返回
            return JwtUtil.creatToken(loginMassage.getUserName(),loginMassage.getUserRole(),loginMassage.getRoleZn());
        }

        return null;
    }
}
