package cn.sea.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "测试控制器TestController")
@RestController
public class TestController {

    @ApiOperation("测试登录成功并保存username到session域中")
    @GetMapping("/test")
    public String test(@ApiParam("用户名") String username, @ApiIgnore HttpServletRequest request) {
        request.getSession().setAttribute("username", username);
        return "login ko ~";
    }
}
