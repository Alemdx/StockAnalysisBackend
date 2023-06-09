package com.example.StockAnalysisBackend.Jwt;

/**
 * @author Alex
 * @date 2023/4/7 上午11:14
 */
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.StockAnalysisBackend.Entity.User;

import java.util.Date;
import java.util.HashMap;

//创建token
public class JwtTokenUtils {

    private static final int EXPIRE_TIME = 60 * 60 * 1000;  // 过期时间5分钟
    private static final String SECRET_KEY = "123"; // 加密的密钥

    public static String sign(User user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        HashMap<String, Object> head = new HashMap<String, Object>() {
            {
                put("typ", "JWT");
                put("alg", "HS256");
            }
        }; // 这块是 JWT 认证的一些声明信息
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                .withHeader(head)
                .withClaim("username", user.getUsername())  // 把用户名整合到 token 里加密
                .withClaim("password", user.getPassword())  // 把用密码整合到 token 里加密，其实密码没必要
                .withExpiresAt(date).sign(algorithm);
    }

//    验证token
    public static boolean verity(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("调用 username:" + jwt.getClaim("username"));  // 整合到 token 里加密的信息都是可以取出来的
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            return false;
        }
    }

}