package main.java.spring.boot.oauth2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_code")
public class OauthCode {

	@Column(name = "code")
	@Id
	String code;
	
	@Column(name="authentication", columnDefinition="mediumblob")
	Byte[] authentication;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Byte[] getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Byte[] authentication) {
		this.authentication = authentication;
	}
	
	

}
