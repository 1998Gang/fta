package cn.ezs.fta.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <h3>fat</h3>
 * <p>用户</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-04 18:47
 **/
@Component
public class User {

    private String userName;
    private String password;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                " userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
