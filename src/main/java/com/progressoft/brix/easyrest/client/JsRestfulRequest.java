package com.progressoft.brix.easyrest.client;

import com.google.gwt.xhr.client.XMLHttpRequest;
import com.progressoft.brix.easyrest.shared.BaseRestfulRequest;

public class JsRestfulRequest extends BaseRestfulRequest {

    private XMLHttpRequest request;

    public JsRestfulRequest(String uri, String method) {
        super(uri, method);
        request = XMLHttpRequest.create();
        getHeaders();
        request.open(method, getUri());
        request.setOnReadyStateChange(xhr -> {
            if (xhr.getReadyState() == XMLHttpRequest.DONE) {
                if (xhr.getStatus() == 200 && successHandler != null) {
                    successHandler.onResponseReceived(new JsResponse(xhr));
                } else {
                    errorHandler.onError(new FailedResponse());
                }
            }
        });
    }

    @Override
    public void sendForm(String encodedFormData) {
        setContentType("application/x-www-form-urlencoded");
        request.send(encodedFormData);
    }

    @Override
    public void sendJson(String json) {
        setContentType("application/json");
        request.send(json);
    }

    private void setContentType(String s) {
        request.setRequestHeader("Content-Type", s);
    }

    @Override
    public void send() {
        request.send();
    }
}
