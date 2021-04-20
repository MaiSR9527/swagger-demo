文章首发：[springboot2.0整合api文档swagger](https://www.maishuren.top/archives/springboot2%E6%95%B4%E5%90%88api%E6%96%87%E6%A1%A3swagger)

# 前言

最近在整理Github的仓库时，看到以前大学的时候写了个SpringBoot和Swagger的demo，想了一下最后决定写一篇文章，记录一下。

# Swagger简介

Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。

总体目标是使客户端和文件系统作为服务器以同样的速度来更新。文件的方法、参数和模型紧密集成到服务器端的代码，允许 API 来始终保持同步。Swagger 让部署管理和使用功能强大的 API 从未如此简单。

# 创建工程

SpringBoot的版本是2.0.8，Swagger的版本是2.9.2

```xml
	<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.8.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
	<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

## Swagger的配置

```java
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
```

## Controller控制层

Swagger主要是在控制层上使用，通过一些注解来为接口提供API文档。下述的代码中主要使用的注解为这两个`@ApiOperation`和 `@ApiImplicitParam`这两个，`@ApiOperation`注解来给API增加说明并通过`@ApiImplicitParams`注解来给参数增加说明

下面试一些Swagger的常用的注解，更详细的可以查看官方的文档。

- @Api：将类标记为Swagger资源
- @ApiImplicitParam：表示API操作中的单个参数
- @ApiImplicitParams：一个包装器，允许列出多个ApiImplicitParam对象
- @ApiModel：提供有关Swagger模型的其他信息，比如描述POJO对象
- @ApiModelProperty： 添加和操作模型属性的数据
- @ApiOperation： 描述针对特定路径的操作或通常是HTTP方法
- @ApiParam： 为操作参数添加其他元数据
- @ApiResponse： 描述操作的可能响应
- @ApiResponses： 一个包装器，允许列出多个ApiResponse对象
- @Authorization： 声明要在资源或操作上使用的授权方案
- @AuthorizationScope： 描述OAuth2授权范
- @ResponseHeader： 表示可以作为响应的一部分提供的标头。
- @ApiProperty： 描述POJO对象中的属性值
- @ApiError ： 接口错误所返回的信息
- ...

```java
@Slf4j
@Api(tags = "用户相关相关的API")
@RestController
public class UserController {

    @ApiOperation(value = "获取用户信息",notes="添加用户信息")
    @PostMapping("/user")
    public String addUser(@RequestBody User user) {
        log.info("POST请求，接收到参数:{}", user.toString());
        return user.toString();
    }

    @ApiOperation(value = "更新用户", notes = "根据User对象更新用户")
    @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    @PutMapping("/user")
    public boolean updateUser(@RequestBody User user) {
        log.info("UPDATE请求，请求参数:{}", user);
        return true;
    }

    @ApiOperation(value = "删除用户", notes = "根据User对象删除用户")
    @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    @DeleteMapping("/user")
    public boolean deleteUser(@RequestBody User user) {
        log.info("DELETE请求，请求参数:{}", user);
        return true;
    }


    @ApiOperation(value = "获取用户列表", notes = "根据User对象查询用户信息")
    @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    @GetMapping("/user")
    public User findByUser(User user) {
        log.info("GET请求，请求参数:{}", user);
        return User.builder()
                .id(1)
                .age(18)
                .name("小明")
                .build();
    }
}
```

## 实体类

```java
@ApiModel("用户实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	@ApiModelProperty("用户ID")
    private Integer id;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("登录密码")
    private String password;
    @ApiModelProperty("年龄")
    private Integer age;
}
```

## 启动类

```java
@SpringBootApplication
public class SwaggerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerDemoApplication.class, args);
    }

}
```

## 测试

启动程序，浏览器访问http://localhost:8080/swagger-ui.html

![](http://cdn.jsdelivr.net/gh/MaiSR9527/blog-pic/blog/swagger-01.png)

点开UserController和Models可以看到在代码中的配置

![](http://cdn.jsdelivr.net/gh/MaiSR9527/blog-pic/blog/swagger-02.png)

选择要测试的接口，点击Try it out。输入参数，

![](http://cdn.jsdelivr.net/gh/MaiSR9527/blog-pic/blog/swagger-03.png)

# 总结

其实，Swagger的注解很多，当把一个API描述好之后，会发现方法上多了一堆的注解。对于我个人来说有点不舒服，感觉有点侵略性的感觉。对于API文档，目前有很多的项目，感觉用起来也不错。