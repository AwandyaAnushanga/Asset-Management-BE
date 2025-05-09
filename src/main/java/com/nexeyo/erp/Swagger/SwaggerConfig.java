//package com.nexeyo.erp.Swagger;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@Configuration
//public class SwaggerConfig {
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo("Fire Rest APIs",
//                "#Fire",
//                "1.0",
//                "Terms of service",
//                new Contact("Sandun Vidusankha", "", "sandun.vidusankha@gmail.com@nexeyo.com"),
//                "License of API",
//                "https://erpnex.com:8443/swagger-ui/",
//                Collections.emptyList());
//    }
//
//    @Bean
////    @Conditional(SwaggerEnableCondition.class)
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
////                .host("erpnex.com:8443")
//                .securityContexts(Arrays.asList(securityContext()))
//                .securitySchemes(Arrays.asList(apiKey()))
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
////    @Bean
////    public Docket apiDev() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .apiInfo(apiInfo())
////                .host("erpnex.com:8443")
////                .securityContexts(Arrays.asList(securityContext()))
////                .securitySchemes(Arrays.asList(apiKey()))
////                .select()
////                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.any())
////                .build();
////    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth()).build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//    }
//
//}