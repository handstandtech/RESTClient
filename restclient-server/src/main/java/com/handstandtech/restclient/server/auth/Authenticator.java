/**
 * Handstand Technologies, LLC
 * http://handstandtech.com
 */
package com.handstandtech.restclient.server.auth;

import com.handstandtech.restclient.shared.model.RequestAuthentication;

public interface Authenticator {
	public void authenticate(Object connection);

	public RequestAuthentication getRequestAuthenticationType();
}
