package com.progressoft.brix.easyrest.server;

import com.progressoft.brix.easyrest.shared.BaseRestfulRequest;

public class JavaRestfulRequest extends BaseRestfulRequest {

    public JavaRestfulRequest(String uri, String method) {
        super(uri, method);
    }

    @Override
    public void sendForm(String encodedFormData) {

    }

    @Override
    public void sendJson(String json) {

    }

    @Override
    public void send() {

    }
}
