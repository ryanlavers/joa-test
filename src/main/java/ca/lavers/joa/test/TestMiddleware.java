package ca.lavers.joa.test;

import ca.lavers.joa.core.Context;
import ca.lavers.joa.core.Middleware;
import ca.lavers.joa.core.NextMiddleware;

public class TestMiddleware implements Middleware {

    private int runCount = 0;
    private boolean runNext = true;

    public TestMiddleware() {
    }

    public TestMiddleware runNext(boolean runNext) {
        this.runNext = runNext;
        return this;
    }

    public void call(Context ctx, NextMiddleware next) {
        runCount++;
        if(runNext) next.run();
    }

    public int getRunCount() {
        return runCount;
    }

    public boolean ran() {
        return runCount > 0;
    }
}