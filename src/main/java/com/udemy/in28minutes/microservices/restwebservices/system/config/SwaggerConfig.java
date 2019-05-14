package com.udemy.in28minutes.microservices.restwebservices.system.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  public static final Contact DEFAULT_CONTACT =
      new Contact("Ferlim", "https://github.com/ferlimputra", "f******@gmail.com");
  private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
      new HashSet<>(Arrays.asList("application/json"));

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Udemy Rest WebServices")
        .description("Udemy in28minutes Rest Webservice Api Documentation").version("1.0.0")
        .license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
        .contact(DEFAULT_CONTACT).build();
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
        .produces(DEFAULT_PRODUCES_AND_CONSUMES).consumes(DEFAULT_PRODUCES_AND_CONSUMES);
  }
}
