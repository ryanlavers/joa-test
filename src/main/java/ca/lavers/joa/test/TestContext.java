package ca.lavers.joa.test;

import ca.lavers.joa.core.Context;
import ca.lavers.joa.core.NamespacedAttributeStore;

public class TestContext extends Context {
    private Exception thrownException;

    TestContext(TestRequest request, NamespacedAttributeStore attrs) {
        super(request, new TestResponse(), attrs);
    }

    @Override
    public TestResponse response() {
        return (TestResponse) super.response();
    }

    public Exception getThrownException() {
        return thrownException;
    }

    void setThrownException(Exception thrownException) {
        this.thrownException = thrownException;
    }
}
