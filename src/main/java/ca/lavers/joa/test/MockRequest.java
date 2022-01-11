package ca.lavers.joa.test;

import ca.lavers.joa.core.Middleware;
import ca.lavers.joa.core.MiddlewareChain;
import ca.lavers.joa.core.NamespacedAttributeStore;

/**
 * Executes middleware(s) with a mock request, afterwards providing the resulting
 * Context, Response, and any thrown exceptions for verification
 */
public class MockRequest {

    private final TestRequest request;
    private final NamespacedAttributeStore ctxAttributes = new NamespacedAttributeStore();

    /**
     * Create a new mock request with the given method and path
     */
    public MockRequest(String method, String path) {
        request = new TestRequest();
        request.method = method;
        request.path = path;
    }

    /**
     * Mock a GET request with the given path
     */
    public static MockRequest get(String path) {
        return new MockRequest("GET", path);
    }

    /**
     * Mock a POST request with the given path
     */
    public static MockRequest post(String path) {
        return new MockRequest("POST", path);
    }

    /**
     * Mock a PUT request with the given path
     */
    public static MockRequest put(String path) {
        return new MockRequest("PUT", path);
    }

    /**
     * Mock a DELETE request with the given path
     */
    public static MockRequest delete(String path) {
        return new MockRequest("DELETE", path);
    }

    /**
     * Set a request header on the mock request
     */
    public MockRequest header(String name, String value) {
        request.headers.put(name, value);
        return this;
    }

    /**
     * Set a query parameter on the mock request
     */
    public MockRequest queryParam(String name, String value) {
        request.queryParams.put(name, value);
        return this;
    }

    /**
     * Set a {@link ca.lavers.joa.core.Context} attribute on the mock request
     */
    public MockRequest contextAttr(String ns, String key, String value) {
        this.ctxAttributes.put(ns, key, value);
        return this;
    }

    /**
     * Set the remote IP address on the mock request
     */
    public MockRequest remoteIp(String ip) {
        request.remoteIp = ip;
        return this;
    }

    /**
     * Set the request body on the mock request
     */
    // TODO -- allow passing an object to be serialized to json?
    public MockRequest body(String body) {
        request.body = body;
        return this;
    }

    /**
     * Execute the given middleware(s) with this mock request and returns the context
     * used. If the middleware throws any exception, this is caught so that the
     * context can still be returned. The caught exception is available from the
     * returned context as {@link TestContext#getThrownException()}.
     *
     * @param middlewares The middleware(s) to execute
     * @return The resulting TestContext containing the Response and
     *         any thrown exception
     */
    public TestContext run(Middleware... middlewares) {
        MiddlewareChain chain = new MiddlewareChain(middlewares);

        TestContext ctx = new TestContext(request, ctxAttributes);
        // Catching any thrown exception so that we can still return the context for inspection
        try {
            chain.call(ctx);
        } catch(Exception e) {
            ctx.setThrownException(e);
        }

        return ctx;
    }

}
