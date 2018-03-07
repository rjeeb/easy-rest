package com.progressoft.brix.easyrest.server;

import com.progressoft.brix.easyrest.shared.Response;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class JavaResponse implements Response {

    private final HttpResponse<Buffer> response;

    public JavaResponse(HttpResponse<Buffer> response) {
        this.response = response;
    }

    @Override
    public String getHeader(String header) {
        return response.getHeader(header);
    }

    @Override
    public Map<String, String> getHeaders() {
        return response.headers().entries().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public String getHeadersAsString() {
        return getHeaders().entrySet().stream().map(o -> o.getKey() + "=" + o.getValue())
                .collect(joining("\n"));
    }

    @Override
    public int getStatusCode() {
        return response.statusCode();
    }

    @Override
    public String getStatusText() {
        return response.statusMessage();
    }

    @Override
    public String getBodyAsString() {
        return response.bodyAsString();
    }
}
