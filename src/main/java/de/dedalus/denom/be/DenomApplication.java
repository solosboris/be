package de.dedalus.denom.be;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.server.servlet.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Dedalus denomination example",
		version = "1.0",
		description = "API documentation"
	)
)
public class DenomApplication {

	@Value("${tomcat.port:8080}")
	private int port;
	@Value("${tomcat.contextPath:/api/denom}")
	private String contextPath;

	public static void main(String[] args) {
		SpringApplication.run(
			DenomApplication.class, args
		);
	}

	/**
	 * enables AJP in embedded Tomcat.
	 *
	 * @return customizer
	 */
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
		return server -> {
			server.setContextPath(contextPath);
			server.setPort(port);
		};
	}

}