# Joa Test

Request mocking to make testing [Joa middleware](https://github.com/ryanlavers/joa-middleware) easier.

### Examples

```java
    @Test
    void test1() {
        MockRequest req = MockRequest.get("/foo/bar")
                .header("Authorization", "Bearer secret");

        TestContext ctx = req.run(new MyMiddleware());
        TestResponse resp = ctx.response();

        assertEquals(200, resp.status());
        assertEquals("yes", resp.header("Cool-Header"));
        assertEquals("Hello world!", resp.body());
        assertNull(ctx.getThrownException());
    }

    @Test
    void test2() {
        MockRequest req = MockRequest.get("/some/other");
        TestContext ctx = req.run(new MyMiddleware());

        assertTrue(ctx.getThrownException() instanceof BadRequestException);
    }
```