package com.consious_coder.ratelimiters.algorithms;


public interface IRateLimiter {
    public boolean allowRequest(Request request);
}