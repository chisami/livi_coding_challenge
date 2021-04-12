package com.livi.coding.challenge.demo.bdd;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RequestCallback;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HeaderBodySettingRequestCallback implements RequestCallback {
	final Map<String, String> requestHeaders;

	private Object body;
	private ObjectMapper objectMapper;

	public HeaderBodySettingRequestCallback(final Map<String, String> headers, final Object body) {
		this.requestHeaders = headers;
		this.body = body;

	}

	@Override
	public void doWithRequest(ClientHttpRequest request) throws IOException, JsonProcessingException {
		final HttpHeaders clientHeaders = request.getHeaders();
		for (final Map.Entry<String, String> entry : requestHeaders.entrySet()) {
			clientHeaders.add(entry.getKey(), entry.getValue());
		}
		if (null != body) {
			objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

			byte[] json = objectMapper.writeValueAsBytes(body);
			request.getBody().write(json);
		}
	}
}
