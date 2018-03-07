package com.progressoft.brix.easyrest;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.progressoft.brix.easyrest.shared.JavaRestfulRequestTest;
import com.progressoft.brix.easyrest.shared.JsRestfulRequestTest;
import junit.framework.Test;

public class RestfulRequestSuite {
    public static Test suite() {
        GWTTestSuite suite = new GWTTestSuite("All restful request tests");
        suite.addTestSuite(JavaRestfulRequestTest.class);
        suite.addTestSuite(JsRestfulRequestTest.class);
        return suite;
    }
}
