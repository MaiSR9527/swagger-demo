package com.msr.demo.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Site: https://www.maishuren.top
 * @Author: maishuren
 * @Date: 2019/11/16 13:38
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        //主要api配置机制初始化为swagger规范2.0
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.msr.demo.swagger.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("msr", "www.baidu,com", "maisrcn@qq.com");
        return new ApiInfoBuilder()
                // 标题
                .title("Spring Boot中使用Swagger2构建RESTful API")
                // 描述信息
                .description("rest api 文档构建利器")
                //服务网址
                .termsOfServiceUrl("http://localhost:8080")
                //版本号
                .version("1.0")
                .contact(contact)
                .build();
    }
}
