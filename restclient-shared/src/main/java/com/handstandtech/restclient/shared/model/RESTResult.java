/**
 * Handstand Technologies, LLC
 * http://handstandtech.com
 */
package com.handstandtech.restclient.shared.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RESTResult implements Serializable {

	/**
	 * Default Serialization UID
	 */
	private static final long serialVersionUID = 1L;

	private RequestAuthentication requestAuthentication;
	private RequestMethod requestMethod;
	private String requestUrl;

	private Integer responseCode;
	private int responseLength;
	private String responseMessage;
	private long responseTime;
	private Map<String, String> responseHeaders = new HashMap<String, String>();
	private String responseBody;
	private String finalUrl;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public void setUrl(String url) {
		this.requestUrl = url;
	}

	public String getUrl() {
		return requestUrl;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setRequestHeaders(Map<String, String> headers) {
		this.responseHeaders = headers;
	}

	public Map<String, String> getRequestHeaders() {
		return responseHeaders;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public int getResponseLength() {
		return responseLength;
	}

	public void setResponseLength(int responseLength) {
		this.responseLength = responseLength;
	}

	public RequestAuthentication getRequestAuthentication() {
		return requestAuthentication;
	}

	public void setRequestAuthentication(RequestAuthentication requestAuthentication) {
		this.requestAuthentication = requestAuthentication;
	}

	public RequestMethod getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(RequestMethod requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public Map<String, String> getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(Map<String, String> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--------------------------------------------\n");
		sb.append("[auth: " + requestAuthentication.name() + "]\n");
		sb.append("[issuing request: " + requestMethod.name() + " " + requestUrl + "]\n");
		if (finalUrl != null) {
			sb.append("[final url: " + finalUrl + "]");
		}
		sb.append("[read " + responseLength + " chars in " + responseTime + "ms]\n");
		sb.append("[response: " + responseCode + " " + responseMessage + "]\n");
		sb.append("\n");

		for (String header : responseHeaders.keySet()) {
			String headerValue = responseHeaders.get(header);
			sb.append(header + ": " + headerValue + "\n");
		}
		sb.append("\n");

		sb.append(responseBody);
		sb.append("\n");
		return sb.toString();
	}

	public void setFinalUrl(String finalUrl) {
		this.finalUrl = finalUrl;
	}

	public String getFinalUrl() {
		return finalUrl;
	}

	public void addResponseHeader(String name, String value) {
		responseHeaders.put(name, value);
	}

}
