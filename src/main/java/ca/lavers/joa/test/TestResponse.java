package ca.lavers.joa.test;

import ca.lavers.joa.core.AbstractResponse;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TestResponse extends AbstractResponse {

    public String body() {
        String text;
        try (Scanner scanner = new Scanner(body, StandardCharsets.UTF_8.name())) {
            text = scanner.useDelimiter("\\A").next();
            scanner.close();
            return text;
        }
    }
}
