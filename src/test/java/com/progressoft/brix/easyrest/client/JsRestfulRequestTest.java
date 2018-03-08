package com.progressoft.brix.easyrest.client;

import com.google.gwt.core.client.GWT;
import com.progressoft.brix.easyrest.shared.RestfulRequestTest;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

public class JsRestfulRequestTest extends RestfulRequestTest {

    @Override
    public String getModuleName() {
        return "com.progressoft.brix.easyrest.EasyRestTest";
    }

    @Override
    protected String getUri() {
        return GWT.getModuleBaseURL() + "testRequest";
    }

    @Override
    protected void wait(int millis) {
        delayTestFinish(millis);
    }

    @Override
    protected void finish() {
        finishTest();
    }

    @Override
    protected String json() {
        Message value = new Message();
        value.message = "test message";
        return stringify(value);
    }

    @Override
    protected String expectedJson() {
        return "{\"message\":\"test message\"}";
    }

    @JsMethod(namespace = "JSON")
    private static native String stringify(Object value);

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Message {
        public String message;
    }
}
