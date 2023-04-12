package com.example.StockAnalysisBackend.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * @author Alex
 * @date 2023/4/11 下午12:06
 */
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private static final String JWT_SECRET = "mySecretKey";

    private static final long JWT_EXPIRATION = 86400000L; // 1 day in milliseconds

    public static String generateToken(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        return JWT.create()
                .withClaim("username", ((UserDetails) authentication.getPrincipal()).getUsername())  // 把用户名整合到 token 里加密
                .withClaim("password", ((UserDetails) authentication.getPrincipal()).getPassword())  // 把用密码整合到 token 里加密，其实密码没必要
                .withExpiresAt(expiryDate).sign(algorithm);
    }

    public static Claim getUsernameFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        Claim username=jwt.getClaim("username");
        return username;
    }

    public static boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("调用 username:" + jwt.getClaim("username"));  // 整合到 token 里加密的信息都是可以取出来的
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            return false;
        }
    }
}
