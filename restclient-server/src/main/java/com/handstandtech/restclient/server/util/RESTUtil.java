/**
 * Handstand Technologies, LLC
 * http://handstandtech.com
 */
package com.handstandtech.restclient.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

public class RESTUtil {

	private static final String UTF_8 = "UTF-8";

	public static String getBaseUrl(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String url = request.getRequestURL().toString();

		String baseUrl = url.replace(uri, "");
		return baseUrl;
	}

	public static String encode(String string) {
		try {
			return URLEncoder.encode(string, UTF_8);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
			return null;
		}
	}

	public static String getRequestPostBody(HttpServletRequest req) throws IOException {
		InputStream is = req.getInputStream();

		StringBuffer body = new StringBuffer();
		String line = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF_8));
		while ((line = br.readLine()) != null) {
			body.append(line + "\n");
		}
		return body.toString();
	}
}
