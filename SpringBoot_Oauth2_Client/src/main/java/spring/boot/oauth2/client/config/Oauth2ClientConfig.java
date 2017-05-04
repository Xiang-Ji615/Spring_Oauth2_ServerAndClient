package main.java.spring.boot.oauth2.client.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
public class Oauth2ClientConfig {
	@Value("${oauth.resource:http://localhost:8080}")
	private String baseUrl;
	@Value("${oauth.authorize:http://localhost:8080/oauth/authorize}")
	private String authorizeUrl;

	@Value("${oauth.token:http://localhost:8080/oauth/token}")
    private String tokenUrl;


    @Bean
    protected OAuth2ProtectedResourceDetails resource() {

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        List<String> scopes = new ArrayList<String>(2);
        scopes.add("write");
        scopes.add("read");
        resource.setAccessTokenUri(tokenUrl);
        resource.setClientId("jj");
        resource.setClientSecret("test");
        resource.setGrantType("password");
        resource.setScope(scopes);

        resource.setUsername("jj");
        resource.setPassword("test");

        return resource;
    }
    

	@Bean
	public OAuth2RestOperations restTemplate() {
		AccessTokenRequest atr = new DefaultAccessTokenRequest();
		return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
	}

}
