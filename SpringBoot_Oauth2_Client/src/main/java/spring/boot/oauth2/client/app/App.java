package main.java.spring.boot.oauth2.client.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.client.OAuth2RestOperations;


@SpringBootApplication
@ComponentScan(basePackages={"main.java"})
public class App implements CommandLineRunner {

	@Autowired
	private OAuth2RestOperations restTemplate;

	public static void main(String[] args) throws Exception {

		// disabled banner, don't want to see the spring logo
		SpringApplication app = new SpringApplication(App.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Sprin boot works");
//		System.out.println(restTemplate.getOAuth2ClientContext());
		System.out.println(restTemplate.getAccessToken());
		System.out.println(restTemplate.getForObject("http://localhost:8080/SpringOauth2/Rest/Test", String.class));

	}

}
