package ca.lavers.joa.test;

import ca.lavers.joa.core.Middleware;
import ca.lavers.joa.core.MiddlewareChain;
import ca.lavers.joa.core.NamespacedAttributeStore;
import ca.lavers.joa.core.errors.HttpException;

public class MockRequest {

    private final TestRequest request;
    private final NamespacedAttributeStore ctxAttributes = new NamespacedAttributeStore();

    public MockRequest(String method, String path) {
        request = new TestRequest();
        request.method = method;
        request.path = path;
    }

    public static MockRequest get(String path) {
        return new MockRequest("GET", path);
    }

    public static MockRequest post(String path) {
        return new MockRequest("POST", path);
    }

    public static MockRequest put(String path) {
        return new MockRequest("PUT", path);
    }

    public static MockRequest delete(String path) {
        return new MockRequest("DELETE", path);
    }

    public MockRequest header(String name, String value) {
        request.headers.put(name, value);
        return this;
    }

    public MockRequest queryParam(String name, String value) {
        request.queryParams.put(name, value);
        return this;
    }

    public MockRequest contextAttr(String ns, String key, String value) {
        this.ctxAttributes.put(ns, key, value);
        return this;
    }

    public MockRequest remoteIp(String ip) {
        request.remoteIp = ip;
        return this;
    }

    public MockRequest body(String body) {
        request.body = body;
        return this;
    }

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
