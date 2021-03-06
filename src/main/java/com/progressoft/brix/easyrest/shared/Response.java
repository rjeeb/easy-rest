package com.progressoft.brix.easyrest.shared;

import java.util.Map;

public interface Response {

    String getHeader(String header);

    Map<String, String> getHeaders();

    int getStatusCode();

    String getStatusText();

    String getBodyAsString();
}
