package cn.ezs.fta.controller;

import cn.ezs.fta.annotation.TokenRequired;
import cn.ezs.fta.pojo.LoginMassage;
import cn.ezs.fta.pojo.User;
import cn.ezs.fta.service.LoginService;
import cn.ezs.fta.service.RegisterService;
import cn.ezs.fta.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.jar.JarEntry;


/**
 * <h3>fat</h3>
 * <p>用户管理控制</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-04 10:07
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  RegisterService registerService;

    @Autowired
    private LoginService loginService;


    /**
     * 注册
     * @param user user
     * @return user的json字符串
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<User> register(@RequestBody User user) {
        boolean register = registerService.register(user);
        if (register){
            return ResponseEntity.status(HttpStatus.OK).body(user);

        }
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(user);
    }

    /**
     * 登录
     * @param user user
     * @return token
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<String> login(@RequestBody User user){

        String token = loginService.login(user);


        if (token==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }


    @RequestMapping("/test")
    public ResponseEntity<String> test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){


        HttpSession session = httpServletRequest.getSession(true);

        /*Cookie cookie = new Cookie("session", session.getId());


        69FB0364255F2402F87DE8E43F757454

        673EC7D9A78A512274CC2B14109A068A

        httpServletResponse.addCookie(cookie);*/



        return ResponseEntity.status(HttpStatus.OK).body("hello security");
    }

    @RequestMapping("/test2")
    public ResponseEntity<String> test2(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie:cookies){
            System.out.println("cookieName:"+cookie.getName());
            System.out.println("cookieValue:"+cookie.getValue());
        }


        return ResponseEntity.status(HttpStatus.OK).body("hello security");
    }


}
