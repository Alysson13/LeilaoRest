package br.com.adal.swagger;

import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("br.com.adal")))
				.paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Leilão API")
				.description("Documentação da API de acesso ao sistema de leilão")
				.version("1.0")
				.build();
	}
}
