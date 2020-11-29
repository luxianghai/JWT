package cn.sea.interceptors;

import cn.sea.utils.JWTUtils;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取请求头中的令牌
        String token = request.getHeader("token");

        Map<String, Object> map = new HashMap<>();

        try {
            DecodedJWT verify = JWTUtils.verify(token); // 验证令牌
            return true; // 放行
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "无效签名！！");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "token过期!!");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "token算法不匹配!!");
        } catch (InvalidClaimException e) {
            e.printStackTrace();
            map.put("msg","失效的token！！！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "无效的token！！");
        }
        map.put("state", false); // 设置状态
        // 将map转为json   jackson
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;character=utr-8");
        response.getWriter().println(json);
        return false;
    }
}
