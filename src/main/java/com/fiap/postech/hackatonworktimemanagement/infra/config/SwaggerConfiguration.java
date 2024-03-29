package com.fiap.postech.hackatonworktimemanagement.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info().title("Sistema de Gerenciamento de Ponto")
            .description("Sistema de gerenciamento de ponto da empresa Hackaton SA")
            .version("1.0.0"));
  }
}
