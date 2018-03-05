package com.progressoft.brix.easyrest;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.progressoft.brix.easyrest.shared.JsRestfulRequestTest;
import com.progressoft.brix.easyrest.shared.RestfulRequestTest;
import junit.framework.Test;

public class RestfulRequestSuite {
    public static Test suite() {
        GWTTestSuite suite = new GWTTestSuite("All restful request tests");
        suite.addTestSuite(RestfulRequestTest.class);
        suite.addTestSuite(JsRestfulRequestTest.class);
        return suite;
    }
}
