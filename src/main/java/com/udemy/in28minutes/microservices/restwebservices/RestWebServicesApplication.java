package com.udemy.in28minutes.microservices.restwebservices;

import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestWebServicesApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestWebServicesApplication.class, args);
  }

  @Bean
  public LocaleResolver resolveLocale() {
    AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
    localeResolver.setDefaultLocale(Locale.US);
    return localeResolver;
  }
}
