package com.progressoft.brix.easyrest.shared;

import java.util.Map;

public interface Response {

    String getHeader(String header);

    Map<String, String> getHeaders();

    String getHeadersAsString();

    int getStatusCode();

    String getStatusText();

    String getBodyAsString();
}
