package com.pragma.powerup.usermicroservice.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@SecurityScheme(name = "jwt", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openApiConfig() {
        Schema<?> mapSchema = new Schema<Map<String, String>>()
                .addProperty("message", new StringSchema().example("string"));
        Schema<?> errorSchema = new Schema<Map<String, String>>()
                .addProperty("error", new StringSchema().example("string"));
        return new OpenAPI()
                .info(new Info()
                        .title(Constants.SWAGGER_TITLE_MESSAGE)
                        .description(Constants.SWAGGER_DESCRIPTION_MESSAGE)
                        .version(Constants.SWAGGER_VERSION_MESSAGE)
                        .license(new License().name(Constants.SWAGGER_LICENSE_NAME_MESSAGE).url(Constants.SWAGGER_LICENSE_URL_MESSAGE))
                        .termsOfService(Constants.SWAGGER_TERMS_OF_SERVICE_MESSAGE))
                        .components(new Components()
                        .addSchemas("Map", mapSchema)
                        .addSchemas("Error", errorSchema));

    }
}
