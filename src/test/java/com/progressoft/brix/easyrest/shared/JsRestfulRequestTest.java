package com.progressoft.brix.easyrest.shared;

import com.google.gwt.core.client.GWT;

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
}
