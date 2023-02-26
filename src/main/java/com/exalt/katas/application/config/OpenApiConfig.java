package com.exalt.katas.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Value("${info.app.version}")
  private String apiVersion;

  @Value("${info.email-api-designer}")
  private String emailApiDesigner;


  @Bean
  public OpenAPI apiInfo() {
    Contact contact = new Contact();
    contact.setEmail(emailApiDesigner);
    contact.setName("eXalt");
    return new OpenAPI()
        .info(new Info()
            .title("Gestion de Compte API")
            .description(
                "Gestion d'un compte : les operations 'consulter transaction' , deposer montant et retrait montant")
            .version(apiVersion));
  }
}
