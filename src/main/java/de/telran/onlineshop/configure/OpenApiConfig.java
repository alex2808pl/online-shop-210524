package de.telran.onlineshop.configure;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Online shop project Api",
                description = "API системы online магазина",
                version = "1.0.0",
                contact = @Contact(
                        name = "Oleksandr Plakushchyi",
                        email = "alex@i.ua",
                        url = "https://myalex.i.ua"
                )
        )
)
public class OpenApiConfig {
}
