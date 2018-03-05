package com.progressoft.brix.easyrest.client;

import com.progressoft.brix.easyrest.shared.BaseRestfulRequest;

public class JsRestfulRequest extends BaseRestfulRequest {

    public JsRestfulRequest(String uri, String method) {
        super(uri, method);
    }

    @Override
    public void sendForm(String encodedFormData) {

    }

    @Override
    public void sendJson(String json) {

    }

    @Override
    public void sendJson(Object object) {

    }

    @Override
    public void send() {

    }
}
