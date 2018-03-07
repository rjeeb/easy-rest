package com.progressoft.brix.easyrest.shared;

import com.google.gwt.junit.client.GWTTestCase;

import java.util.Arrays;
import java.util.logging.Logger;

public abstract class RestfulRequestTest extends GWTTestCase {

    protected static final Logger LOGGER = Logger.getLogger(RestfulRequestTest.class.getCanonicalName());
    private static final String GET = "GET";

    private RestfulRequest restfulRequest;

    @Override
    protected void gwtSetUp() {
        restfulRequest = RestfulRequest.get(getUri());
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
        assertEquals(getUri(), restfulRequest.getUri());
    }

    public void testAddQueryParameter() {
        restfulRequest.addQueryParam("key", "value");
        assertEquals(getUri() + "?key=value", restfulRequest.getUri());
    }

    public void testAddMultipleQueryParameter() {
        restfulRequest.addQueryParam("key1", "value1");
        restfulRequest.addQueryParam("key2", "value2");

        assertEquals(getUri() + "?key1=value1&key2=value2", restfulRequest.getUri());
    }

    public void testAddQueryParameters() {
        restfulRequest.addQueryParams("key", Arrays.asList("value1", "value2"));
        assertEquals(getUri() + "?key=value1&key=value2", restfulRequest.getUri());
    }

    public void testAddParameterWithNullValue() {
        restfulRequest.addQueryParam("key", null);
        assertEquals(getUri(), restfulRequest.getUri());
    }

    public void testSetQueryParamater() {
        restfulRequest.addQueryParam("key", "value1");
        restfulRequest.setQueryParam("key", "value2");
        assertEquals(getUri() + "?key=value2", restfulRequest.getUri());
    }

    public void testCreateWithUriThatHasParam_shouldParseParameters() {
        assertEquals("key1=value1&key2=value2", create(getUri() + "?key1=value1&key2=value2").getQuery());
    }

    public void testGetPath() {
        assertEquals(getUri(), create(getUri() + "?key1=value1&key2=value2").getPath());
    }

    public void testGetPathEndsWithSlash_slashShouldBeTrimmed() {
        assertEquals(getUri(), create(getUri() + "/").getPath());
    }

    public void testCreateRequestWithUriAndQueryParam() {
        RestfulRequest baseRestfulRequest = create(getUri()).addQueryString("key1=value1&key2=value2");
        assertEquals(getUri(), baseRestfulRequest.getPath());
        assertEquals("key1=value1&key2=value2", baseRestfulRequest.getQuery());
    }

    public void testCreateWithInvalidMethod_shouldThrowException() {
        try {
            create(getUri(), null);
            fail("Method must be null");
        } catch (IllegalArgumentException e) {
        }

        try {
            create(getUri(), "   ");
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

    public void testSendWithNoBody() {
        RestfulRequest test_content = RestfulRequest.get(getUri()).onSuccess(response -> {
            assertEquals("test content", response.getBodyAsString());
            finish();
        }).onError(throwable -> fail());
        test_content.send();
        wait(500);
    }

    protected abstract String getUri();

    /**
     * Put the current test in asynchronous mode. If the test method completes
     * normally, this test will not immediately succeed. Instead, a <i>delay
     * period</i> begins. During the delay period, if the {@link #finish()} method
     * is called before the delay period expires,
     * the test will succeed, otherwise the test will fail</li>
     * <p>
     * This method is typically used to test event driven functionality.
     * </p>
     */
    protected abstract void wait(int millis);

    protected abstract void finish();
}
