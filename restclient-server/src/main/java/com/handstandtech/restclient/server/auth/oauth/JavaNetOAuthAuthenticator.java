/**
 * Handstand Technologies, LLC
 * http://handstandtech.com
 */
package com.handstandtech.restclient.server.auth.oauth;

import java.net.HttpURLConnection;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import com.handstandtech.restclient.server.auth.Authenticator;
import com.handstandtech.restclient.shared.model.RequestAuthentication;

public class JavaNetOAuthAuthenticator implements Authenticator {

	protected OAuthConsumer consumer;

	public JavaNetOAuthAuthenticator(OAuthConsumer consumer) {
		this.setConsumer(consumer);
	}

	public void setConsumer(OAuthConsumer consumer) {
		this.consumer = consumer;
	}

	public OAuthConsumer getConsumer() {
		return consumer;
	}

	@Override
	public void authenticate(Object connectionObject) {
		if (connectionObject instanceof HttpURLConnection) {
			HttpURLConnection connection = (HttpURLConnection) connectionObject;
			if (consumer != null) {
				try {
					consumer.sign(connection);
				} catch (OAuthMessageSignerException e) {

					e.printStackTrace();
				} catch (OAuthExpectationFailedException e) {

					e.printStackTrace();
				} catch (OAuthCommunicationException e) {

					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public RequestAuthentication getRequestAuthenticationType() {
		return RequestAuthentication.OAUTH;
	}

}
