package com.github.stackscrubs.stuq.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring configuration class.
 * Used to enable and configure Swagger
 * and generation of API documentation.
 */
@EnableSwagger2
@Configuration
public class SpringFoxConfig {     

    /**
     * Bean used by Swagger to configure generation of API docs.
     * @return Configured Docket object used by Swagger.
     */
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.github.stackscrubs.stuq.backend.controller"))              
          .paths(PathSelectors.any())
          .build();                     
    }
}