package cn.sea.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {

    private static final String TOKEN = "toke423nf4234s4324d!$gsaf42342JFKAJ23@FDsadf&";

    /**
     * 生成 token 的方法  header.payload.signature
     * @param map payload
     * @return
     */
    public static String getToken(Map<String,String> map) {

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 2); // 设置过期时间，2天过期

        // 创建JWT builder
        JWTCreator.Builder builder = JWT.create();
        // payload
        map.forEach( (k,v) -> {
            builder.withClaim(k, v);
        });

        String token = builder.withExpiresAt(instance.getTime())   // 指定过期时间
                .sign(Algorithm.HMAC256(TOKEN));// 签名 sign

        return token;
    }

    /**
     * 验证token 合法性,并将token的信息返回
     */
    public static DecodedJWT verify(String token) {
        // 验证签名
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }

    /**
     * 获取token信息的方法
     */
    /*public static DecodedJWT getTokenInfo(String token) {

        DecodedJWT verify = JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
        return verify;
    }*/

}
