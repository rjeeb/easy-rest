package com.progressoft.brix.easyrest.server;

import com.progressoft.brix.easyrest.shared.BaseRestfulRequest;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;

public class JavaRestfulRequest extends BaseRestfulRequest {

    private final HttpRequest<Buffer> request;
    private static final WebClient WEB_CLIENT;

    static {
        WEB_CLIENT = WebClient.create(Vertx.vertx());
    }

    public JavaRestfulRequest(String uri, String method) {
        super(uri, method);
        request = WEB_CLIENT.requestAbs(HttpMethod.valueOf(method), uri);
    }

    @Override
    public void sendForm(String encodedFormData) {
    }

    @Override
    public void sendJson(String json) {
    }

    @Override
    public void send() {
        request.send(event -> {
            if (event.succeeded())
                successHandler.onResponseReceived(new JavaResponse(event.result()));
            else
                errorHandler.onError(event.cause());
        });
    }
}
