package ca.lavers.joa.test;

import ca.lavers.joa.core.AbstractRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of Request for middleware testing purposes
 */
public class TestRequest extends AbstractRequest {

    String path;
    String method;
    Map<String, String> queryParams = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    String remoteIp;
    String body;

    @Override
    public String path() {
        return path;
    }

    @Override
    public String method() {
        return method;
    }

    @Override
    public Map<String, String> queryParams() {
        return Collections.unmodifiableMap(queryParams);
    }

    @Override
    public Map<String, String> headers() {
        return Collections.unmodifiableMap(headers);
    }

    @Override
    public String header(String name) {
        return headers.get(name);
    }

    @Override
    public String remoteIp() {
        return remoteIp;
    }

    @Override
    public InputStream body() throws IOException {
        return new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
    }
}
