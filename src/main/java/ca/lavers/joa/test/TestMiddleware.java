package ca.lavers.joa.test;

import ca.lavers.joa.core.Context;
import ca.lavers.joa.core.Middleware;
import ca.lavers.joa.core.NextMiddleware;

/**
 * A simple middleware that just keeps track of if it has been invoked
 * and calls the next middleware if desired.
 */
public class TestMiddleware implements Middleware {

    private int runCount = 0;
    private boolean runNext = true;

    /**
     * Creates a new TestMiddleware that will call next
     */
    public TestMiddleware() {
    }

    /**
     * Sets whether this TestMiddleware will call the next middleware in the
     * chain when invoked.
     */
    public TestMiddleware runNext(boolean runNext) {
        this.runNext = runNext;
        return this;
    }

    public void call(Context ctx, NextMiddleware next) {
        runCount++;
        if(runNext) next.run();
    }

    /**
     * Returns how many times this TestMiddleware has been invoked
     */
    public int getRunCount() {
        return runCount;
    }

    /**
     * Returns whether or not this TestMiddleware has been invoked
     */
    public boolean ran() {
        return runCount > 0;
    }
}