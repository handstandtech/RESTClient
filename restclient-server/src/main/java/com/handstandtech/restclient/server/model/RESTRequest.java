/**
 * Handstand Technologies, LLC
 * http://handstandtech.com
 */
package com.handstandtech.restclient.server.model;

import java.io.Serializable;
import java.util.Map;

import com.handstandtech.restclient.server.auth.Authenticator;
import com.handstandtech.restclient.shared.model.RequestMethod;

public class RESTRequest implements Serializable {

	/**
	 * Default Serialization UID
	 */
	private static final long serialVersionUID = 1L;

	protected String url;

	protected RequestMethod method;

	protected Authenticator auth;

	protected byte[] payload;

	protected Map<String, String> headers;

	public RESTRequest() {
	}

	public RESTRequest(RequestMethod method, String urlString,
			Authenticator auth) {
		this.method = method;
		this.url = urlString;
		this.auth = auth;
	}

	public RESTRequest(RequestMethod method, String urlString,
			Authenticator auth, byte[] payload) {
		this.method = method;
		this.url = urlString;
		this.auth = auth;
		this.payload = payload;
	}

	public RESTRequest(RequestMethod method, String urlString) {
		this.method = method;
		this.url = urlString;

	}

	public RESTRequest(RequestMethod method, String urlString, byte[] payload) {
		this.method = method;
		this.url = urlString;
		this.payload = payload;
	}

	public RESTRequest(RequestMethod method, String urlString,
			Map<String, String> headers) {
		this.method = method;
		this.url = urlString;
		this.headers = headers;

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RequestMethod getMethod() {
		return method;
	}

	public void setMethod(RequestMethod method) {
		this.method = method;
	}

	public Authenticator getAuth() {
		return auth;
	}

	public void setAuth(Authenticator auth) {
		this.auth = auth;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
}
