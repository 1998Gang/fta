package cn.ezs.fta.Interceptor;

import cn.ezs.fta.annotation.TokenRequired;
import cn.ezs.fta.util.JwtUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>fta</h3>
 * <p>jwt拦截器，验证token</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-07 19:54
 **/
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final ObjectMapper OBJECTMAPPER=new ObjectMapper();


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws IOException {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");


        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();

        PrintWriter out;
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(TokenRequired.class)) {
            TokenRequired userLoginToken = method.getAnnotation(TokenRequired.class);
            if (userLoginToken.required()) {

                // 执行认证
                if (("null").equals(token)||token == null) {
                    Map<String,String> massage=new HashMap<>();
                    massage.put("message","无token，请登录");
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    httpServletResponse.setStatus(401);
                    out=httpServletResponse.getWriter();
                    out.append(OBJECTMAPPER.writeValueAsString(massage));
                    return false;
                }

                // 验证 token
                try {
                    if(!JwtUtil.verify(token)){
                        Map<String,String> massage=new HashMap<>();
                        massage.put("message","token过期，请重新登录");
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        httpServletResponse.setStatus(401);
                        out=httpServletResponse.getWriter();
                        out.append(OBJECTMAPPER.writeValueAsString(massage));
                        return false;
                    }
                } catch (JWTVerificationException e) {
                    return false;
                }
                return true;
            }
        }
        return true;
    }

}
