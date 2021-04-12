package com.livi.coding.challenge.demo.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.OAuth2Scheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

/**
 * The Class SpringFoxConfig.
 */
@Configuration
@EnableOpenApi
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {

	private String accessTokenUri = "http://localhost:8089/oauth/token";

	@Bean
	public Docket productApi() {

		return new Docket(DocumentationType.OAS_30).enable(true).select()
				.apis(RequestHandlerSelectors.basePackage("com.livi.coding.challenge.demo.controller"))
				.paths(PathSelectors.any()).build().securityContexts(Collections.singletonList(securityContext()))
				.securitySchemes(Arrays.asList(securitySchema()))
				.apiInfo(getApiInfo());

	}

	// private OAuth securitySchema() {
	@Bean
	public SecurityScheme securitySchema() {

		List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		authorizationScopeList.add(new AuthorizationScope("R", "read all"));
		authorizationScopeList.add(new AuthorizationScope("U", "update all"));

		// List<GrantType> grantTypes = new ArrayList<>();
		// GrantType passwordCredentialsGrant = new
		// ClientCredentialsGrant(accessTokenUri);
		// grantTypes.add(passwordCredentialsGrant);

		return OAuth2Scheme.OAUTH2_CLIENT_CREDENTIALS_FLOW_BUILDER.tokenUrl(accessTokenUri)
				.scopes(authorizationScopeList).build();

	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {

		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
		authorizationScopes[0] = new AuthorizationScope("R", "read all");
		authorizationScopes[1] = new AuthorizationScope("U", "update all");

		return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
	}

	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder().clientId("app").clientSecret("secret").scopeSeparator(",")
				.useBasicAuthenticationWithAccessCodeGrant(true).build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("Credit Service RESTful APIs ", "For Livi backend coding challenge.", "v1.0",
				"TERMS OF SERVICE URL", new Contact("NAME", "URL", "EMAIL"), "LICENSE", "LICENSE URL",
				Collections.emptyList());
	}

}