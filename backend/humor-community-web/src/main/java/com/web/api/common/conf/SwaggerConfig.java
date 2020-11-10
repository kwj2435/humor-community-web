package com.web.api.common.conf;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private String version;
	private String title;
	
	@Bean
    public Docket api() { 
		title = "단단한 커뮤니티 사이트 API Docs";
		version = "V1";
		
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.ant("/v1/api/**"))                          
          .build()
          .apiInfo(apiInfo(title,version));
    }
	
	private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "단단한 커뮤니티 사이트 API Docs ",
                version,
                "www.example.com",
                new Contact("Contact Me", "www.example.com", "foo@example.com"),
                "",
                "www.example.com",
                new ArrayList<>());
    }
}
