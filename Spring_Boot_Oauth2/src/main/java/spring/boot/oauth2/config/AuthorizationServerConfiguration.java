package main.java.spring.boot.oauth2.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

//import main.java.y2tocommerceconnect.oauth2.service.Y2ToCommerceConnectClientDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private static String REALM = "MY_OAUTH_REALM";

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

//	@Autowired
	// @Qualifier("Y2ToCommerceConnectClientDetailsService")
	private ClientDetailsService clientDetailsService;

	@Autowired
	@Qualifier("dataSourceSecurity")
	private DataSource dataSource;


	public AuthorizationServerConfiguration() throws Exception {
		// TODO Auto-generated constructor stub
	
	}
	
	@PostConstruct
	public void postConstruct() throws Exception{
//				clientDetailsService = new JdbcClientDetailsServiceBuilder().dataSource(dataSource).withClient("jj")
////				clientDetailsService = new InMemoryClientDetailsServiceBuilder().withClient("jj")
//						.secret("$2a$10$mqNwcSc7GYEPcdep3n2v5.4i2/S33B0Rzyhxn6pVQFyzNmLwZVnma")
//						.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//						.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("read", "write", "trust")
//						.accessTokenValiditySeconds(120).refreshTokenValiditySeconds(600).and().withClient("jj2")
//						.secret("$2a$10$mqNwcSc7GYEPcdep3n2v5.4i2/S33B0Rzyhxn6pVQFyzNmLwZVnma")
//						.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//						.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("read", "write", "trust")
//						.accessTokenValiditySeconds(120).refreshTokenValiditySeconds(600).and().build();
				
				clientDetailsService = new JdbcClientDetailsServiceBuilder().dataSource(dataSource).build();

	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.withClientDetails(clientDetailsService);

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
				.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.passwordEncoder(new BCryptPasswordEncoder());
		oauthServer.realm(REALM + "/client");
	}
}
