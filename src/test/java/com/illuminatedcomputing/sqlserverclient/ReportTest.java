package com.illuminatedcomputing.sqlserverclient;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ReportTest extends TestCase {
  
    public ReportTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ReportTest.class);
    }

    public void testApp() {
        assertTrue(true);
    }
}
