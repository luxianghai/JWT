package cn.sea;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;

public class SpringBootJwtTest {

    // 生成令牌
    @Test
    public void test1() {

        HashMap<String, Object> map = new HashMap<>();

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 200);

        String token = JWT.create()
                // .withHeader(map) // header，一般不用设置
                .withClaim("userId", 21) // 设置用户id（payload）
                .withClaim("username", "xiaochen") // 设置用户名（payload）
                .withExpiresAt(instance.getTime()) // 指定令牌过期时间
                .sign(Algorithm.HMAC256("token!$@FDsadf&")); // 签名
        System.out.println(token);
    }

    // 令牌验证
    @Test
    public void test2() {
        // 先验证签名
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("token!$@FDsadf&")).build();

        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDY2MTcxNTEsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGlhb2NoZW4ifQ.A_p9WbDebRitPmk7DkKIYIm69Mezjth2YwX75kKJNMg");

        System.out.println(verify.getClaim("userId").asInt()); // 获取userId
        System.out.println(verify.getClaim("username").asString()); // 获取 username
        System.out.println("过期时间：" + verify.getExpiresAt());
        /*System.out.println(verify.getClaims().get("userId").asInt());
        System.out.println(verify.getClaims().get("username").asString());*/
    }

    @Test
    public void test3() {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.getTime());
        instance.add(Calendar.SECOND, 200);
        System.out.println(instance.getTime());
    }
}
