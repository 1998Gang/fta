package cn.ezs.fta.mapper;


import cn.ezs.fta.pojo.LoginMassage;
import org.apache.ibatis.annotations.Param;



/**
 * 角色用户对应表操作
 * @author 1998Gang
 */
public interface RoleUserMapper {
    /**
     * 添加角色 用户对应关系
     * @param username 用户名
     * @param rid 角色id
     */
    void insertRoleUser(@Param("userName") String username, @Param("rid") int rid);


    /**
     * 查询用户的角色，权限
     * @param userName usrname
     * @return
     */
    LoginMassage selectByUsername(@Param("userName") String userName);
}
