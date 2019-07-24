package org.pokemonrun.config;

import org.springframework.boot.web.server.*;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpStatus;

@Configuration
public class WebConfiguration {
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
            factory.addErrorPages(error404Page);
        };
    }
}
