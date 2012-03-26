package com.handstandtech.restclient.server.auth;

/**
 * Handstand Technologies, LLC
 * http://handstandtech.com
 */

import java.net.HttpURLConnection;
import com.handstandtech.restclient.server.util.Base64;

import com.handstandtech.restclient.shared.model.RequestAuthentication;

public class BasicAuthenticator implements Authenticator {

	private String username;

	private String password;

	public BasicAuthenticator(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public void authenticate(HttpURLConnection connection) {
		byte[] usernamePasswordBytes = (username + ":" + password).getBytes();
		String encodedCredential = Base64.encodeToString(usernamePasswordBytes, Base64.DEFAULT);
		connection.setRequestProperty("Authorization", "BASIC " + encodedCredential);
	}

	@Override
	public RequestAuthentication getRequestAuthenticationType() {
		return RequestAuthentication.BASIC;
	}

}
