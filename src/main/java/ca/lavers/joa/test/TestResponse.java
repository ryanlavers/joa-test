package ca.lavers.joa.test;

import ca.lavers.joa.core.AbstractResponse;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Implementation of Response for middleware testing purposes
 */
public class TestResponse extends AbstractResponse {

    // TODO -- Override the body setters to provide access to the original object
    //         passed to body() so tests don't have to deserialize

    /**
     * Returns the entire response body as a String
     */
    public String body() {
        String text;
        try (Scanner scanner = new Scanner(body, StandardCharsets.UTF_8.name())) {
            text = scanner.useDelimiter("\\A").next();
            scanner.close();
            return text;
        }
    }
}
