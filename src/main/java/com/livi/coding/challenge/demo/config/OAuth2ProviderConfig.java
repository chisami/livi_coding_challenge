package com.livi.coding.challenge.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.livi.coding.challenge.demo.exeception.AuthExceptionEntryPoint;
import com.livi.coding.challenge.demo.security.EndpointAuthority;


/**
 * The Class OAuth2ProviderConfig.
 */
@Configuration
public class OAuth2ProviderConfig {
	
	/** The Constant DEMO_RESOURCE_ID. */
	private static final String DEMO_RESOURCE_ID = "rest_service";

	/**
	 * The Class ResourceServerConfiguration.
	 */
	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		/* (non-Javadoc)
		 * @see org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer)
		 */
		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
			resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
		}

		/* (non-Javadoc)
		 * @see org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
		 */
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().authorizeRequests()
			.antMatchers("/creditservice/**").authenticated();
		}

	}

	/**
	 * The Class AuthorizationServerConfiguration.
	 */
	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		/** The token store. */
		private TokenStore tokenStore = new InMemoryTokenStore();

		/** The authentication manager. */
		@Autowired
		private AuthenticationManager authenticationManager;

		/** The user details service. */
		@Autowired
		private UserDetailsService userDetailsService;

		/* (non-Javadoc)
		 * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer)
		 */
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.tokenStore(this.tokenStore).authenticationManager(this.authenticationManager)
			.userDetailsService(userDetailsService);
			
		}

		/* (non-Javadoc)
		 * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer)
		 */
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient("app").authorizedGrantTypes("client_credentials", "refresh_token")
			.authorities("USER").scopes(EndpointAuthority.AUTHORITY_READ, EndpointAuthority.AUTHORITY_UPDATE)
			.resourceIds(DEMO_RESOURCE_ID).secret("secret")
			// .accessTokenValiditySeconds(60)
			.accessTokenValiditySeconds(24 * 365 * 60 * 60)
			// .refreshTokenValiditySeconds(3600)
			;

		}

		/* (non-Javadoc)
		 * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer)
		 */
		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			PasswordEncoder passwordEncoder = new PasswordEncoder() {
				@Override
				public String encode(CharSequence charSequence) {
					return charSequence != null ? charSequence.toString() : null;
				}

				@Override
				public boolean matches(CharSequence charSequence, String s) {
					return charSequence != null && s != null && charSequence.toString().equals(s) ? true : false;
				}
			};
			oauthServer.passwordEncoder(passwordEncoder);
			oauthServer.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()")
			.allowFormAuthenticationForClients();
			oauthServer.authenticationEntryPoint(new AuthExceptionEntryPoint());
		}

		/**
		 * Token services.
		 *
		 * @return the default token services
		 */
		@Bean
		@Primary
		public DefaultTokenServices tokenServices() {
			DefaultTokenServices tokenServices = new DefaultTokenServices();
			tokenServices.setSupportRefreshToken(true);
			tokenServices.setTokenStore(this.tokenStore);
			return tokenServices;
		}

	}

}
