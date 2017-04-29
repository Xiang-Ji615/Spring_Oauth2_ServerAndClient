package main.java.spring.boot.oauth2.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="persistent_logins")
public class PersistentLogins {
	
	@Column(name="username")
	String userName;

	@Column(name="series")
	String series;
	
	@Id
	@Column(name="token")
	String token;
	
	@Column(name="last_used")
	@Type(type = "timestamp")
	Date lastUsed;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}
	
	

}
