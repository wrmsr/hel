package hel.inject;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class InjectorTest
        extends TestCase {
    public InjectorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(InjectorTest.class);
    }

    public void testApp() {
        assertTrue(true);
    }
}
