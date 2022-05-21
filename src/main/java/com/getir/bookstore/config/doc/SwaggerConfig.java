package com.getir.bookstore.config.doc;

import io.swagger.models.Path;
import io.swagger.v3.oas.models.Paths;
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

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.OAS_30)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiMeta());
  }

  private ApiInfo apiMeta() {
    return new ApiInfoBuilder()
        .title("Book Store")
        .description("Book Store - API reference")
        .termsOfServiceUrl("http://book-store.com/tos")
        .contact(new Contact("Nishant Sharma", "http://book-store.com", "nishant.sharma280991@gmail.com"))
        .license("Book Store License")
        .licenseUrl("bookstore@email.com")
        .version("1.0")
        .build();
  }
}
