package com.obf.movie.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RequestInterceptor implements ClientHttpRequestInterceptor {

    private final static Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    private final ApplicationProperties applicationProperties;

    public RequestInterceptor(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//        if (applicationProperties.getVasApi().getUrl().contains(request.getURI().getHost())) { //Only need authentication for VasApi requests
//            request.getHeaders().add("Authorization", "Bearer " + applicationProperties.getVasApi().getToken());
//        }
        ClientHttpResponse response;
        if (applicationProperties.getApiRequestLog()) {

            String req = traceRequest(request, body);
            response = execution.execute(request, body);
            String resp = traceResponse(response);
            log.debug(req + resp + System.lineSeparator());
        } else {
            response = execution.execute(request, body);
            traceFailedRequest(request, body, response);
        }
        return response;
    }

    private void traceFailedRequest(HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is2xxSuccessful() || response.getStatusCode() == HttpStatus.NOT_FOUND)
            return; //This is an expected error..

        String req = traceRequest(request, body);
        String resp = traceResponse(response);
        log.error(req + resp + System.lineSeparator());
    }

    private String traceRequest(HttpRequest request, byte[] body) throws IOException {
        String bodyText = (body != null && body.length > 0) ? ("Request body: " + new String(body, "UTF-8") + System.lineSeparator()) : "";
        return System.lineSeparator() +
            "============================ Request begin ============================" + System.lineSeparator() +
            "URI         : " + request.getURI() + System.lineSeparator() +
            "Method      : " + request.getMethod() + System.lineSeparator() +
            bodyText +
            "============================ Request end ============================";
    }

    private String traceResponse(ClientHttpResponse response) throws IOException {

        return System.lineSeparator() +
            "============================ Response begin ============================" + System.lineSeparator() +
            "Status code  : " + response.getStatusCode() + System.lineSeparator() +
            "Status text  : " + response.getStatusCode().getReasonPhrase() + System.lineSeparator() +
            "============================ Response " + "" + " ============================";
    }
}
