package cn.ezs.fta.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.util.*;

/**
 * <h3>fat</h3>
 * <p>jwt工具类</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-04 20:30
 **/
public class JwtUtil {

    /**
     * token过期时间,200分钟
     */
    private static final long EXPIRE_TIME=12000000;

    /**
     * 密钥
     */
    private static final String KEY_JWT = "jwt_key";

    /**
     * 加密密钥  （本来这些操作应该放在方法体里里面，因为在实际项目中，密钥是不同的）
     */
    private static final Algorithm ALGORITHM =Algorithm.HMAC256(KEY_JWT);

    /**
     * 解密密钥   （本来这些操作应该放在方法体里里面，因为在实际项目中，密钥是不同的）
     */
    private static final JWTVerifier VERIFIER = JWT.require(ALGORITHM).build();


    /**
     * 生成签名
     * @param username 用户名
     * @param userRole 用户角色
     * @param roleZn   用户角色，中文
     * @return token
     */
    public static String creatToken(String username, String userRole, String roleZn){

        //这是一个时间戳，指向该taken创建后的15分钟，也就是15分钟后，该taken失效
        Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);

        //设置taken的头信息
        Map<String,Object> header=new HashMap<>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");

        //附带用户名，用户角色，生成签名。
        return JWT.create()
                .withHeader(header)
                .withClaim("username",username)
                .withClaim("userRole",userRole)
                .withClaim("roleZn",roleZn)
                .withExpiresAt(date)
                .sign(ALGORITHM);
    }


    /**
     * 校验签名是否正确
     * @param token 接受到的token
     * @return boolean
     */
    public static boolean verify(String token){
        try {
            VERIFIER.verify(token);
            return true;
        }catch (IllegalArgumentException | JWTVerificationException e){
            return false;
        }
    }
}
