/**
 * Copyright (C) 2010 Handstand Technologies, LLC
 * http://handstandtech.com
 */
package com.handstandtech.restclient.server.impl;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handstandtech.restclient.server.RESTClient;
import com.handstandtech.restclient.server.auth.Authenticator;
import com.handstandtech.restclient.server.model.RESTRequest;
import com.handstandtech.restclient.shared.model.RESTResult;
import com.handstandtech.restclient.shared.model.RequestMethod;

/**
 * Abstract Implementation, Forwards on Requests to Main Handler.
 * 
 * @author Sam Edwards
 * @version 2011.03.09
 */
public abstract class RESTClientBaseImpl implements RESTClient {

	protected static Logger log = LoggerFactory
			.getLogger(RESTClientBaseImpl.class);

	public RESTClientBaseImpl() {
		super();
	}

	/**
	 * Main Method for doing HTTP Requests
	 * 
	 * @param method
	 * @param urlString
	 * @param auth
	 * @param payload
	 * @return
	 * @throws IOException
	 */
	protected abstract RESTResult internalDoRequest(RequestMethod method,
			String urlString, Authenticator auth, byte[] payload,
			Map<String, String> headers) throws IOException;

	@Override
	public RESTResult request(RESTRequest request) {
		RESTResult result = null;
		try {
			result = internalDoRequest(request.getMethod(), request.getUrl(),
					request.getAuth(), request.getPayload(),
					request.getHeaders());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * @see RESTClient#request(RequestMethod, String, Authenticator)
	 */
	@Override
	public RESTResult request(RequestMethod method, String urlString,
			Authenticator auth) {
		RESTResult result = null;
		try {
			result = internalDoRequest(method, urlString, auth, null, null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * @see RESTClient#requestWithBody(RequestMethod, String, Authenticator,
	 *      byte[])
	 */
	@Override
	public RESTResult requestWithBody(RequestMethod method, String urlString,
			Authenticator auth, byte[] payload) {
		try {
			return internalDoRequest(method, urlString, auth, payload, null);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * @see RESTClient#request(RequestMethod, String)
	 */
	@Override
	public RESTResult request(RequestMethod method, String urlString) {
		try {
			return internalDoRequest(method, urlString, null, null, null);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * @see RESTClient#requestWithBody(RequestMethod, String, byte[])
	 */
	@Override
	public RESTResult requestWithBody(RequestMethod method, String urlString,
			byte[] payload) {
		try {
			return internalDoRequest(method, urlString, null, payload, null);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public RESTResult request(RequestMethod method, String urlString,
			Map<String, String> headers) {
		try {
			return internalDoRequest(method, urlString, null, null, headers);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
