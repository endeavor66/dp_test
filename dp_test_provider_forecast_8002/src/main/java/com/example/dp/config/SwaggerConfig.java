package com.example.dp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(newArrayList(

                        new ParameterBuilder()
                                .name("pageNumber")
                                .description("pageNumber")
                                .modelRef(new ModelRef("int"))
                                .parameterType("header")
                                .required(false)
                                .build(),

                        new ParameterBuilder()
                                .name("pageSize")
                                .description("pageSize")
                                .modelRef(new ModelRef("int"))
                                .parameterType("header")
                                .required(false)
                                .build(),

                        new ParameterBuilder()
                                .name("orders")
                                .description("orders")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build(),

                        new ParameterBuilder()
                                .name("isAsc")
                                .description("true: ASC, false: DESC")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build(),

                        new ParameterBuilder()
                                .name("language")
                                .description("language")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build(),

                        new ParameterBuilder()
                                .name("token")
                                .description("passport token")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build()

                ))
                .groupName("plm")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .build()
                .apiInfo(myApiInfo());
    }



    private ApiInfo myApiInfo() {
        ApiInfo apiInfo = new ApiInfo("Harmontronics-plm 接口",//大标题
                "",//小标题
                "1.0",//版本
                "",
                "",//作者
                "",//链接显示文字
                ""//网站链接
        );
        return apiInfo;
    }
}

