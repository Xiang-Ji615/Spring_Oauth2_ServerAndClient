package main.java.spring.boot.oauth2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_access_token")
public class OauthAccessToken {

	@Column(name = "token_id")
	String tokenId;

	@Column(name = "token", columnDefinition = "mediumblob")
	Byte[] token;

	@Id
	@Column(name = "authentication_id")
	String authenticationId;

	@Column(name = "user_name")
	String userName;

	@Column(name = "client_id")
	String clientId;

	@Column(name = "authentication", columnDefinition = "mediumblob")
	Byte[] authentication;

	@Column(name = "refresh_token")
	String refreshToken;

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

	public String getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Byte[] getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Byte[] authentication) {
		this.authentication = authentication;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
