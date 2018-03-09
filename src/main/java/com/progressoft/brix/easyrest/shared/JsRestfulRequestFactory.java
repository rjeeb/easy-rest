package com.progressoft.brix.easyrest.shared;

import com.progressoft.brix.easyrest.client.JsRestfulRequest;

class JsRestfulRequestFactory extends RestfulRequestFactory {

    @Override
    RestfulRequest request(String uri, String method) {
        return new JsRestfulRequest(uri, method);
    }

    @Override
    RestfulRequest get(String uri) {
        return request(uri, RestfulRequest.GET);
    }

    @Override
    RestfulRequest post(String uri) {
        return request(uri, RestfulRequest.POST);
    }

    @Override
    RestfulRequest delete(String uri) {
        return request(uri, RestfulRequest.DELETE);
    }

    @Override
    RestfulRequest head(String uri) {
        return request(uri, RestfulRequest.HEAD);
    }

    @Override
    RestfulRequest put(String uri) {
        return request(uri, RestfulRequest.PUT);
    }

    @Override
    RestfulRequest options(String uri) {
        return request(uri, RestfulRequest.OPTIONS);
    }
}
