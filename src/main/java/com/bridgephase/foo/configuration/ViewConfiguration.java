package com.bridgephase.foo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ComponentScan(basePackages = { "com.bridgephase.foo.view.controller" })
public class ViewConfiguration {

	@Bean
	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false);
	}

}
