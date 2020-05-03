package com.fib.springbootjavers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class SpringBootJaversApplication {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fib.springbootjavers.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Spring Boot Javers REST API",
                "Spring Boot Javers REST API",
                "0.0.1",
                "Terms of service",
                new Contact("Fakhrul Islam Babar", "www.fib.com", "fakhrulislam.babar@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJaversApplication.class, args);
    }

}
