package br.com.med.voll.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(new Components()
                         .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Voll.med API")
                        .description("API-REST de clínica fictícia, contendo funcionalidades de CRUD de médicos, pacientes e consultas.")
                        .contact(new Contact()
                                .name("Carlos Jessiel Nuñez Soares")
                                .email("c.jessiel_nunez@hotmail.com")
                                .url("https://www.linkedin.com/in/carlos-jessiel-nunez-soares/")));
    }
}
