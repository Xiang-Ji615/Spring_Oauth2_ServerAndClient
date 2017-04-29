package main.java.spring.boot.oauth2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_refresh_token")
public class OauthRefreshToken {

	@Column(name = "token_id")
	@Id
	String tokenId;

	@Column(name = "token", columnDefinition = "mediumblob")
	Byte[] token;

	@Column(name = "authentication", columnDefinition = "mediumblob")
	Byte[] authentication;

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public Byte[] getToken() {
		return token;
	}

	public void setToken(Byte[] token) {
		this.token = token;
	}

	public Byte[] getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Byte[] authentication) {
		this.authentication = authentication;
	}

}
