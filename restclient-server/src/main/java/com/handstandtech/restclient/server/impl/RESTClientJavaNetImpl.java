/**
 * Copyright (C) 2010 Handstand Technologies, LLC
 * http://handstandtech.com
 */
package com.handstandtech.restclient.server.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.handstandtech.restclient.server.auth.Authenticator;
import com.handstandtech.restclient.shared.model.RESTResult;
import com.handstandtech.restclient.shared.model.RequestAuthentication;
import com.handstandtech.restclient.shared.model.RequestMethod;

public class RESTClientJavaNetImpl extends RESTClientBaseImpl {

	public RESTClientJavaNetImpl() {
		super();
	}

	public RESTResult internalDoRequest(RequestMethod method, String urlString, Authenticator auth, byte[] payload, Map<String, String> requestHeaders) throws IOException {
		// Information about the Result of the Call
		RESTResult result = new RESTResult();
		URL url = new URL(urlString);
		result.setRequestUrl(url.toString());

		// sigh. openConnection() doesn't actually open the connection,
		// just gives you a URLConnection. connect() will open the connection.
		result.setRequestMethod(method);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(10000); // Ten Seconds
		connection.setReadTimeout(15000); // 15 Seconds
		connection.setRequestMethod(method.name());

		if (requestHeaders != null) {
			for (String key : requestHeaders.keySet()) {
				String value = requestHeaders.get(key);
				connection.setRequestProperty(key, value);
			}
		}
		// Authenticate if required
		if (auth != null) {
			auth.authenticate(connection);
			result.setRequestAuthentication(auth.getRequestAuthenticationType());
		} else {
			result.setRequestAuthentication(RequestAuthentication.NONE);
		}

		// write body if we're doing POST or PUT
		if (payload != null) {
			byte buffer[] = new byte[8192];
			int read = 0;
			InputStream is = new ByteArrayInputStream(payload);
			if (is != null) {
				connection.setDoOutput(true);
				OutputStream output = connection.getOutputStream();
				while ((read = is.read(buffer)) != -1) {
					output.write(buffer, 0, read);
				}
			}
		}

		// do request
		long time = System.currentTimeMillis();

		StringBuffer responseBody = new StringBuffer();
		try {
			connection.connect();
			InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				responseBody.append(line + "\n");
			}
			connection.disconnect();
		} catch (IOException e) {
			// Let it Continue
		} finally {
			result.setResponseCode(connection.getResponseCode());
			result.setResponseMessage(connection.getResponseMessage());
		}
		time = System.currentTimeMillis() - time;

		result.setResponseLength(responseBody.length());
		result.setResponseTime(time);

		// look at headers
		Map<String, String> headers = new HashMap<String, String>();
		String header = null;
		String headerValue = null;
		int index = 0;
		while ((headerValue = connection.getHeaderField(index)) != null) {
			header = connection.getHeaderFieldKey(index);

			if (header == null) {
				// the 0th header has a null key, and the value is the response
				// line
				// ("HTTP/1.1 200 OK" or whatever)
				// log.info(headerValue);
			} else {
				result.addResponseHeader(header, headerValue);
				headers.put(header, headerValue);
			}
			index++;
		}

		// dump body
		result.setResponseBody(responseBody.toString());
		return result;
	}
}
