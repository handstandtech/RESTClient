/**
 * Handstand Technologies, LLC
 * http://handstandtech.com
 */
package com.handstandtech.restclient.server.auth;

import java.net.HttpURLConnection;

import com.handstandtech.restclient.shared.model.RequestAuthentication;

public class NullAuthenticator implements Authenticator {

	public NullAuthenticator() {
		super();
	}

	@Override
	public void authenticate(HttpURLConnection connection) {
	}

	@Override
	public RequestAuthentication getRequestAuthenticationType() {
		return RequestAuthentication.NONE;
	}

}
