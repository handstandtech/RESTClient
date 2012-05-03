/**
 * Handstand Technologies, LLC
 * http://handstandtech.com
 */
package com.handstandtech.restclient.server.auth;

import com.handstandtech.restclient.shared.model.RequestAuthentication;

public class NullAuthenticator implements Authenticator {

	public NullAuthenticator() {
		super();
	}

	@Override
	public void authenticate(Object connection) {
	}

	@Override
	public RequestAuthentication getRequestAuthenticationType() {
		return RequestAuthentication.NONE;
	}

}
