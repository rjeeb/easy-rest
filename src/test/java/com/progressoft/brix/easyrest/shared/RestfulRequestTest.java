package com.progressoft.brix.easyrest.shared;

import com.google.gwt.junit.client.GWTTestCase;

import java.util.Arrays;

public class RestfulRequestTest extends GWTTestCase {

    private static final String URI = "http://localhost:8080";
    private static final String GET = "GET";

    private RestfulRequest restfulRequest;

    @Override
    public String getModuleName() {
        return null;
    }

    @Override
    protected void gwtSetUp() {
        restfulRequest = RestfulRequest.get(URI);
    }

    private RestfulRequest create(String uri) {
        return create(uri, GET);
    }

    private RestfulRequest create(String uri, String method) {
        return RestfulRequest.request(uri, method);
    }

    public void testCreateWithInvalidUri() {
        try {
            RestfulRequest.request(null, null);
            fail("URI must not be null");
        } catch (IllegalArgumentException e) {
        }

        try {
            RestfulRequest.request("   ", null);
            fail("URI must not be empty");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testValidUri() {
        assertEquals(URI, restfulRequest.getUri());
    }

    public void testAddQueryParameter() {
        restfulRequest.addQueryParam("key", "value");
        assertEquals(URI + "?key=value", restfulRequest.getUri());
    }

    public void testAddMultipleQueryParameter() {
        restfulRequest.addQueryParam("key1", "value1");
        restfulRequest.addQueryParam("key2", "value2");

        assertEquals(URI + "?key1=value1&key2=value2", restfulRequest.getUri());
    }

    public void testAddQueryParameters() {
        restfulRequest.addQueryParams("key", Arrays.asList("value1", "value2"));
        assertEquals(URI + "?key=value1&key=value2", restfulRequest.getUri());
    }

    public void testAddParameterWithNullValue() {
        restfulRequest.addQueryParam("key", null);
        assertEquals(URI, restfulRequest.getUri());
    }

    public void testSetQueryParamater() {
        restfulRequest.addQueryParam("key", "value1");
        restfulRequest.setQueryParam("key", "value2");
        assertEquals(URI + "?key=value2", restfulRequest.getUri());
    }

    public void testCreateWithUriThatHasParam_shouldParseParameters() {
        assertEquals("key1=value1&key2=value2", create(URI + "?key1=value1&key2=value2").getQuery());
    }

    public void testGetPath() {
        assertEquals(URI, create(URI + "?key1=value1&key2=value2").getPath());
    }

    public void testGetPathEndsWithSlash_slashShouldBeTrimmed() {
        assertEquals(URI, create(URI + "/").getPath());
    }

    public void testCreateRequestWithUriAndQueryParam() {
        RestfulRequest baseRestfulRequest = create(URI).addQueryString("key1=value1&key2=value2");
        assertEquals(URI, baseRestfulRequest.getPath());
        assertEquals("key1=value1&key2=value2", baseRestfulRequest.getQuery());
    }

    public void testCreateWithInvalidMethod_shouldThrowException() {
        try {
            create(URI, null);
            fail("Method must be null");
        } catch (IllegalArgumentException e) {
        }

        try {
            create(URI, "   ");
            fail("Method must be empty");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testPutHeader() {
        restfulRequest.putHeader("key", "value");
        assertEquals(1, restfulRequest.getHeaders().size());
        assertEquals("value", restfulRequest.getHeaders().get("key"));
    }

    public void testSetTimeoutWithPositiveValue() {
        restfulRequest.timeout(5);
        assertEquals(5, restfulRequest.getTimeout());
    }

    public void testSetTimeoutWithNegativeValue() {
        restfulRequest.timeout(-2);
        assertEquals(0, restfulRequest.getTimeout());
    }
}
