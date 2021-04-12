package com.livi.coding.challenge.demo.bdd;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livi.coding.challenge.demo.DemoApplication;

import io.cucumber.spring.CucumberContextConfiguration;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@CucumberContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {

	protected static ResponseResults latestResponse = null;
	protected final static String OAUTH2_PARAM_CORRECT = "grant_type=client_credentials&scope=R&client_id=app&client_secret=secret";
	protected final static String OAUTH2_PARAM_WRONG = "grant_type=client_credentials&scope=U&client_id=app&client_secret=secret";
	protected final static String CREDIT_CALCULATOR_URL = "http://localhost:8089/creditservice/v1/calculator";
	protected final static String OAUTH2_TOKEN_URL = "http://localhost:8089/oauth/token";

	// @Autowired
	// protected RestTemplate restTemplate;
	//
	protected RestTemplate restTemplate = new RestTemplate();

	protected void executeGet(String url) throws IOException {
		final Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		final HeaderBodySettingRequestCallback requestCallback = new HeaderBodySettingRequestCallback(headers, null);
		final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

		restTemplate.setErrorHandler(errorHandler);
		latestResponse = restTemplate.execute(url, HttpMethod.GET, requestCallback,
				new ResponseExtractor<ResponseResults>() {
			@Override
			public ResponseResults extractData(ClientHttpResponse response) throws IOException {
				if (errorHandler.hadError) {
					return (errorHandler.getResults());
				} else {
					return (new ResponseResults(response));
				}
			}
		});

	}

	protected void executePost(String url, Object obj, String token) throws IOException {
		final Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		
		if(token!= null) {
			headers.put("Authorization", "Bearer " + token);
		}
		
		final HeaderBodySettingRequestCallback requestCallback = new HeaderBodySettingRequestCallback(headers, obj);
		final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();
		final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);
		
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
		}

		restTemplate.setErrorHandler(errorHandler);
		restTemplate.setRequestFactory(requestFactory);
		
//		RequestCallback requestCallback1 = restTemplate.httpEntityCallback(null, String.class);
		
		latestResponse = restTemplate.execute(url, HttpMethod.POST, requestCallback,
				new ResponseExtractor<ResponseResults>() {
			@Override
			public ResponseResults extractData(ClientHttpResponse response) throws IOException {
				if (errorHandler.hadError) {
					return (errorHandler.getResults());
				} else {
					return (new ResponseResults(response));
				}
			}
		});

	}

	private class ResponseResultErrorHandler implements ResponseErrorHandler {
		private ResponseResults results = null;
		private Boolean hadError = false;

		private ResponseResults getResults() {
			return results;
		}

		@Override
		public boolean hasError(ClientHttpResponse response) throws IOException {
			hadError = response.getRawStatusCode() >= 400;
			return hadError;
		}

		@Override
		public void handleError(ClientHttpResponse response) throws IOException {
			results = new ResponseResults(response);
		}
	}
	
	
	protected String retrieveToken(String requestBody) throws Throwable {

		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody
				.create(requestBody, mediaType);
		Request request = new Request.Builder().url(OAUTH2_TOKEN_URL).method("POST", body)
				.addHeader("Content-Type", "application/x-www-form-urlencoded").build();
		Response response = client.newCall(request).execute();
		ObjectMapper objectMapper = new ObjectMapper();

		@SuppressWarnings("unchecked")
		Map<String, String> entity = objectMapper.readValue(response.body().string(), Map.class);

		return entity.get("access_token");
	}

}
