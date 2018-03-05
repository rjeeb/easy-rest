package com.progressoft.brix.easyrest.shared;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.joining;

public abstract class BaseRestfulRequest implements RestfulRequest {

    private static final String AND = "&";
    private static final String EQUALS = "=";
    private static final String QUESTION_MARK = "?";

    private String uri;
    private String method;
    private Map<String, List<String>> params = new LinkedHashMap<>();
    private Map<String, String> headers = new LinkedHashMap<>();
    private int timeout;
    private SuccessHandler successHandler;
    private ErrorHandler errorHandler;

    public BaseRestfulRequest(String uri, String method) {
        if (isNull(uri) || uri.trim().isEmpty())
            throw new IllegalArgumentException("Invalid URI");
        if (isNull(method) || method.trim().isEmpty())
            throw new IllegalArgumentException("Invalid http method");
        if (uri.contains(QUESTION_MARK)) {
            String[] uriParts = uri.split("\\?");
            addQueryString(uriParts[1]);
            setUri(uriParts[0]);
        } else
            setUri(uri);

        this.method = method;
    }

    @Override
    public BaseRestfulRequest addQueryString(String queryString) {
        String[] params = queryString.split(AND);
        for (String param : params) {
            String[] paramValuePair = param.split(EQUALS);
            addQueryParam(paramValuePair[0], paramValuePair[1]);
        }
        return this;
    }

    private void setUri(String uri) {
        this.uri = uri.endsWith("/") ? uri.substring(0, uri.length() - 1) : uri;
    }

    @Override
    public String getUri() {
        String queryParams = paramsAsString();
        return uri + (queryParams.isEmpty() ? queryParams : QUESTION_MARK + queryParams);
    }

    private String paramsAsString() {
        if (params.isEmpty())
            return "";
        return params.entrySet().stream()
                .map(this::entryAsString)
                .collect(joining(AND));
    }

    private String entryAsString(Map.Entry<String, List<String>> entry) {
        return entry.getValue().stream()
                .map(value -> entry.getKey() + EQUALS + value)
                .collect(joining(AND));
    }

    @Override
    public BaseRestfulRequest addQueryParam(String key, String value) {
        if (isNull(value))
            return this;
        if (!params.containsKey(key))
            params.put(key, new LinkedList<>());
        params.get(key).add(value);
        return this;
    }

    @Override
    public BaseRestfulRequest addQueryParams(String key, Iterable<String> values) {
        for (String value : values)
            addQueryParam(key, value);
        return this;
    }

    @Override
    public BaseRestfulRequest setQueryParam(String key, String value) {
        params.put(key, new LinkedList<>());
        addQueryParam(key, value);
        return this;
    }

    @Override
    public String getQuery() {
        return paramsAsString();
    }

    @Override
    public String getPath() {
        return this.uri;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public BaseRestfulRequest putHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public BaseRestfulRequest timeout(int timeout) {
        if (timeout < 0)
            timeout = 0;
        this.timeout = timeout;
        return this;
    }

    @Override
    public int getTimeout() {
        return timeout;
    }

    @Override
    public BaseRestfulRequest onSuccess(SuccessHandler successHandler) {
        this.successHandler = successHandler;
        return this;
    }

    @Override
    public BaseRestfulRequest onError(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }
}
