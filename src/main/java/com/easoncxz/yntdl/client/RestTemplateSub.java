package com.easoncxz.yntdl.client;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestTemplateSub extends RestTemplate {
	
	private RestTemplate wrapped;
	
	private RestClientImpl client;
	
	RestTemplateSub(RestTemplate wrapped, RestClientImpl client) {
		this.client = client;
		this.wrapped = wrapped;
	}
	
	public RestClientImpl getClientImpl() {
		return this.client;
	}

	@Override
	public void setMessageConverters(
			List<HttpMessageConverter<?>> messageConverters) {
		// TODO Auto-generated method stub
		wrapped.setMessageConverters(messageConverters);
	}

	@Override
	public List<HttpMessageConverter<?>> getMessageConverters() {
		// TODO Auto-generated method stub
		return wrapped.getMessageConverters();
	}

	@Override
	public void setErrorHandler(ResponseErrorHandler errorHandler) {
		// TODO Auto-generated method stub
		wrapped.setErrorHandler(errorHandler);
	}

	@Override
	public ResponseErrorHandler getErrorHandler() {
		// TODO Auto-generated method stub
		return wrapped.getErrorHandler();
	}

	@Override
	public <T> T getForObject(String url, Class<T> responseType,
			Object... urlVariables) throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.getForObject(url, responseType, urlVariables);
	}

	@Override
	public <T> T getForObject(String url, Class<T> responseType,
			Map<String, ?> urlVariables) throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.getForObject(url, responseType, urlVariables);
	}

	@Override
	public <T> T getForObject(URI url, Class<T> responseType)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.getForObject(url, responseType);
	}

	@Override
	public <T> ResponseEntity<T> getForEntity(String url,
			Class<T> responseType, Object... urlVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.getForEntity(url, responseType, urlVariables);
	}

	@Override
	public <T> ResponseEntity<T> getForEntity(String url,
			Class<T> responseType, Map<String, ?> urlVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.getForEntity(url, responseType, urlVariables);
	}

	@Override
	public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.getForEntity(url, responseType);
	}

	@Override
	public HttpHeaders headForHeaders(String url, Object... urlVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.headForHeaders(url, urlVariables);
	}

	@Override
	public HttpHeaders headForHeaders(String url, Map<String, ?> urlVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.headForHeaders(url, urlVariables);
	}

	@Override
	public HttpHeaders headForHeaders(URI url) throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.headForHeaders(url);
	}

	@Override
	public URI postForLocation(String url, Object request,
			Object... urlVariables) throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.postForLocation(url, request, urlVariables);
	}

	@Override
	public URI postForLocation(String url, Object request,
			Map<String, ?> urlVariables) throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.postForLocation(url, request, urlVariables);
	}

	@Override
	public URI postForLocation(URI url, Object request)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.postForLocation(url, request);
	}

	@Override
	public <T> T postForObject(String url, Object request,
			Class<T> responseType, Object... uriVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.postForObject(url, request, responseType, uriVariables);
	}

	@Override
	public <T> T postForObject(String url, Object request,
			Class<T> responseType, Map<String, ?> uriVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.postForObject(url, request, responseType, uriVariables);
	}

	@Override
	public <T> T postForObject(URI url, Object request, Class<T> responseType)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.postForObject(url, request, responseType);
	}

	@Override
	public <T> ResponseEntity<T> postForEntity(String url, Object request,
			Class<T> responseType, Object... uriVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.postForEntity(url, request, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> postForEntity(String url, Object request,
			Class<T> responseType, Map<String, ?> uriVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.postForEntity(url, request, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> postForEntity(URI url, Object request,
			Class<T> responseType) throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.postForEntity(url, request, responseType);
	}

	@Override
	public void put(String url, Object request, Object... urlVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		wrapped.put(url, request, urlVariables);
	}

	@Override
	public void put(String url, Object request, Map<String, ?> urlVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		wrapped.put(url, request, urlVariables);
	}

	@Override
	public void put(URI url, Object request) throws RestClientException {
		// TODO Auto-generated method stub
		wrapped.put(url, request);
	}

	@Override
	public void delete(String url, Object... urlVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		wrapped.delete(url, urlVariables);
	}

	@Override
	public void delete(String url, Map<String, ?> urlVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		wrapped.delete(url, urlVariables);
	}

	@Override
	public void delete(URI url) throws RestClientException {
		// TODO Auto-generated method stub
		wrapped.delete(url);
	}

	@Override
	public Set<HttpMethod> optionsForAllow(String url, Object... urlVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.optionsForAllow(url, urlVariables);
	}

	@Override
	public Set<HttpMethod> optionsForAllow(String url,
			Map<String, ?> urlVariables) throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.optionsForAllow(url, urlVariables);
	}

	@Override
	public Set<HttpMethod> optionsForAllow(URI url) throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.optionsForAllow(url);
	}

	@Override
	public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
			HttpEntity<?> requestEntity, Class<T> responseType,
			Object... uriVariables) throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.exchange(url, method, requestEntity, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
			HttpEntity<?> requestEntity, Class<T> responseType,
			Map<String, ?> uriVariables) throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.exchange(url, method, requestEntity, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> exchange(URI url, HttpMethod method,
			HttpEntity<?> requestEntity, Class<T> responseType)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.exchange(url, method, requestEntity, responseType);
	}

	@Override
	public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
			HttpEntity<?> requestEntity,
			ParameterizedTypeReference<T> responseType, Object... uriVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.exchange(url, method, requestEntity, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
			HttpEntity<?> requestEntity,
			ParameterizedTypeReference<T> responseType,
			Map<String, ?> uriVariables) throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.exchange(url, method, requestEntity, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> exchange(URI url, HttpMethod method,
			HttpEntity<?> requestEntity,
			ParameterizedTypeReference<T> responseType)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.exchange(url, method, requestEntity, responseType);
	}

	@Override
	public <T> T execute(String url, HttpMethod method,
			RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor, Object... urlVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.execute(url, method, requestCallback, responseExtractor,
				urlVariables);
	}

	@Override
	public <T> T execute(String url, HttpMethod method,
			RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor, Map<String, ?> urlVariables)
			throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.execute(url, method, requestCallback, responseExtractor,
				urlVariables);
	}

	@Override
	public <T> T execute(URI url, HttpMethod method,
			RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor) throws RestClientException {
		// TODO Auto-generated method stub
		return wrapped.execute(url, method, requestCallback, responseExtractor);
	}

}
