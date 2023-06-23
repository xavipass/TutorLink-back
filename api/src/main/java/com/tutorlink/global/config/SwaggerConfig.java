package com.tutorlink.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select() // ApiSelectorBuilder 생성
                .apis(RequestHandlerSelectors.basePackage("com.app.api")) //API 패키지 경로 todo 패키지 경로 수정
                .paths(PathSelectors.ant("/api/**")) //path 조건에 따라서 API 문서화 todo API 경로 수정
                .build()
                ;
    }





}
