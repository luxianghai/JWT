package cn.sea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact CONTACT = new Contact("大海", "http://8.129.218.31:88/image/", "3243016771@qq.com");

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("LXH")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.sea.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("柒拾贰的SwaggerAPI文档",
                "放弃不难，但坚持一定很酷",
                "v1.0",
                "",
                CONTACT,
                "",
                "",
                new ArrayList()
        );
    }
}
