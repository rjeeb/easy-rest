package com.progressoft.brix.easyrest.client;

public class FailedResponse extends Throwable {
    public FailedResponse(String responseText) {
        super(responseText);
    }
}
