package org.openapitools.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {

    @Bean
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("SRGSSR EPG V3")
                                .description("This API provides programmatic access to retrieve Electronic Program Guide (EPG) for TV and Radio stations. Supported business units are SRF, RSI and RTS.")
                                .termsOfService("https://developer.srgssr.ch/sites/default/files/Terms_SRGSSR_APIPortal_ENG.pdf")
                                .contact(
                                        new Contact()
                                                .email("api@srgssr.ch")
                                )
                                .version("3.0.7")
                )
                .components(
                        new Components()
                                .addSecuritySchemes("srg_auth", new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                )
                )
        ;
    }
}