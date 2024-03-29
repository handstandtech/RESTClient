/**
 * Handstand Technologies, LLC
 * http://handstandtech.com
 */
package com.handstandtech.restclient.server.auth;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import com.handstandtech.restclient.server.util.RESTUtil;
import com.handstandtech.restclient.shared.model.RequestAuthentication;

public class AWSAuthenticator implements Authenticator {

	private String AwsAccessKeyId;

	private String signature;

	private Map<String, String> headers = new HashMap<String, String>();

	public AWSAuthenticator(String AwsAccessKeyId, String signature) {
		this.setAwsAccessKeyId(AwsAccessKeyId);
		this.setSignature(signature);
	}

	@Override
	public void authenticate(Object conn) {
		HttpURLConnection connection = (HttpURLConnection)conn;
		String encodedSignature = RESTUtil.encode(signature);
		connection.setRequestProperty("Authorization", "AWS " + AwsAccessKeyId + ":" + encodedSignature);

		// Add other headers
		for (String key : headers.keySet()) {
			String value = headers.get(key);
			connection.setRequestProperty(key, value);
		}
	}

	@Override
	public RequestAuthentication getRequestAuthenticationType() {
		return RequestAuthentication.BASIC;
	}

	public String getAwsAccessKeyId() {
		return AwsAccessKeyId;
	}

	public void setAwsAccessKeyId(String awsAccessKeyId) {
		AwsAccessKeyId = awsAccessKeyId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void addHeader(String key, String value) {
		headers.put(key, value);
	}
}
