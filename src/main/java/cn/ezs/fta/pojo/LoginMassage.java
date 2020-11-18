package cn.ezs.fta.pojo;

/**
 * <h3>fta</h3>
 * <p>登录信息</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-05 19:21
 **/
public class LoginMassage {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户角色
     */
    private String userRole;

    /**
     * 用户角色中文名
     */
    private String roleZn;

    /**
     * token
     */
    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getRoleZn() {
        return roleZn;
    }

    public void setRoleZn(String roleZn) {
        this.roleZn = roleZn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginMassage{" +
                "userName='" + userName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", roleZn='" + roleZn + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
