package main.java.spring.boot.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

	 public static final String RESOURCE_ID = "my_rest_api";
     
	    @Override
	    public void configure(ResourceServerSecurityConfigurer resources) {
	        resources.resourceId(RESOURCE_ID).stateless(false);
	    }
	 
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	        http.
	        anonymous().disable()
	        .requestMatchers().antMatchers("/Rest/**")
	        .and().authorizeRequests()
	        .antMatchers("/Rest/**").access("hasRole('ADMIN')")
	        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	    }
}
