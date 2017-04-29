package main.java.spring.boot.oauth2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_token")
public class OauthClientToken {

	@Column(name = "token_id")
	String tokenId;
	@Column(name = "token", columnDefinition="mediumblob")
	byte[] token;

	@Id
	@Column(name = "authentication_id")
	String authenticationId;

	@Column(name = "user_name")
	String userName;

	@Column(name = "client_id")
	String clientId;

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public byte[] getToken() {
		return token;
	}

	public void setToken(byte[] token) {
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

}
