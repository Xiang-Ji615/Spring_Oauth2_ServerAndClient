package main.java.spring.boot.oauth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private DriverManagerDataSource dataSource;
	
	@Autowired
	@Qualifier("dataSourceSecurity")
	private DataSource dataSourceSecurity;

	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Autowired
	private SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler;
	
	@Autowired
	private DaoAuthenticationProvider authenticationProvider;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
//	@Autowired
//	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN").and().withUser("bob")
//				.password("abc123").roles("USER");
//	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().anonymous().disable().authorizeRequests().antMatchers("/oauth/token").permitAll()
//		.and().formLogin().permitAll();
//	}
	
//	<s:http auto-config="true" authentication-manager-ref="generalManager">
//	<s:intercept-url pattern="/Web/Index" access="hasRole('ROLE_ADMIN')" />
//	<s:form-login login-page="/User/Login"
//		password-parameter="password" username-parameter="username"
//		authentication-failure-url="/User/Login?error" login-processing-url="/User/Login/j_spring_security_check"
//		authentication-success-handler-ref="savedRequestAwareAuthenticationSuccessHandler" />
//	<s:logout logout-success-url="/User/Login?logout"
//		logout-url="/User/Logout" delete-cookies="JSESSIONID" />
//	<s:csrf request-matcher-ref="csrfMatcher" />
//
//	<s:remember-me token-validity-seconds="1209600"
//		remember-me-parameter="remember-me" data-source-ref="dataSourceSecurity" />
//	</s:http>

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/Web/Index").hasRole("ADMIN")
		.and()
		.formLogin().loginPage("/User/Login")
		.passwordParameter("password").usernameParameter("username")
		.loginProcessingUrl("/User/Login/j_spring_security_check")
		.failureUrl("/User/Login?error").successHandler(savedRequestAwareAuthenticationSuccessHandler).and()
		.logout()
		.logoutSuccessUrl("/User/Login?msg").logoutUrl("/User/Logout").deleteCookies("JSESSIONID").and()
		.csrf()
		.ignoringAntMatchers("/oauth/token", "/oauth/authorize")
		.and().rememberMe().tokenValiditySeconds(1209600).rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository())
		.and().formLogin().permitAll()
		.and().authenticationProvider(authenticationProvider)
		;
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSourceSecurity);
		return db;
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public TokenStore tokenStore() {
		 return new JdbcTokenStore(dataSourceSecurity);
	}

	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}

	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}

}
