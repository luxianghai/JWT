package cn.sea.controller;

import cn.sea.entity.User;
import cn.sea.service.UserService;
import cn.sea.utils.JWTUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api("用户控制器UserController")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
    })*/
    @ApiOperation("登录接口")
    @GetMapping("/login")
    public Map<String, Object> login(User user) {
        //log.info("用户名：【" + user.getName() + "】 , 密码：【"+user.getPassword()+"】");
        Map<String, Object> map = new HashMap<>();

        try {
            User userDB = userService.login(user);

            Map<String, String> payload = new HashMap<>();
            payload.put("id", userDB.getId().toString());
            payload.put("name", userDB.getName());
            // 生成jwt令牌
            String token = JWTUtils.getToken(payload);

            //System.out.println("token = " + token); // 响应token
            map.put("state", true);
            map.put("msg", "认证成功");
            map.put("token", token);
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", "认证失败");
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation("测试接口")
    @PostMapping("/test")
    public Map<String, Object> test(@ApiIgnore HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();

        // 处理业务逻辑
        // 获取 token 中携带的数据
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        String id = verify.getClaim("id").asString();
        String username = verify.getClaim("name").asString();
        log.info("name = " + username);
        log.info("id = " + id);

        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }

    /*@ApiOperation("测试接口")
    @PostMapping("/test")
    public Map<String, Object> test(@ApiParam("jwt令牌") String token) {

        Map<String, Object> map = new HashMap<>();
        log.info("当前token = " + token);

        try {
            DecodedJWT verify = JWTUtils.verify(token);
            map.put("state", true);
            map.put("msg", "请求成功");
            return map;
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
        map.put("state", false);
        return map;
    }*/
}
