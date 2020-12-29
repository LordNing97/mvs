package com.xy.mvs.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author 陈璇
 * @Date 2020/4/2 14:58
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.basePackage}")
    private String basePackage;

    @Value("${swagger.licenseUrl}")
    private String licenseUrl;

    @Bean
    public Docket studentRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口")
                .description("接口")
                .license("美乐云购")
                .build();
    }

}
